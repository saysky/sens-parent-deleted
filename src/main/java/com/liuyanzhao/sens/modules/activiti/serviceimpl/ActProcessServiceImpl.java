package com.liuyanzhao.sens.modules.activiti.serviceimpl;

import com.liuyanzhao.sens.common.constant.ActivitiConstant;
import com.liuyanzhao.sens.common.exception.SensException;
import com.liuyanzhao.sens.common.utils.SecurityUtil;
import com.liuyanzhao.sens.common.vo.SearchVo;
import com.liuyanzhao.sens.modules.activiti.dao.ActProcessDao;
import com.liuyanzhao.sens.modules.activiti.entity.ActBusiness;
import com.liuyanzhao.sens.modules.activiti.entity.ActProcess;
import com.liuyanzhao.sens.modules.activiti.service.ActNodeService;
import com.liuyanzhao.sens.modules.activiti.service.ActProcessService;
import com.liuyanzhao.sens.modules.activiti.utils.MessageUtil;
import com.liuyanzhao.sens.modules.activiti.vo.ProcessNodeVo;
import com.liuyanzhao.sens.modules.base.entity.DepartmentHeader;
import com.liuyanzhao.sens.modules.base.entity.Role;
import com.liuyanzhao.sens.modules.base.entity.User;
import com.liuyanzhao.sens.modules.base.service.DepartmentHeaderService;
import com.liuyanzhao.sens.modules.base.service.UserRoleService;
import com.liuyanzhao.sens.modules.base.service.UserService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * 流程管理接口实现
 * @author 言曌
 */
@Slf4j
@Service
@Transactional
public class ActProcessServiceImpl implements ActProcessService {

    @Autowired
    private ActProcessDao actProcessDao;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ActNodeService actNodeService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentHeaderService departmentHeaderService;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public ActProcessDao getRepository() {
        return actProcessDao;
    }

    @Override
    public Page<ActProcess> findByCondition(Boolean showLatest, ActProcess actProcess, SearchVo searchVo, Pageable pageable) {

        return actProcessDao.findAll(new Specification<ActProcess>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ActProcess> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("name");
                Path<String> processKeyField = root.get("processKey");
                Path<String> categoryIdField = root.get("categoryId");
                Path<Integer> statusField = root.get("status");
                Path<Date> createTimeField = root.get("createTime");
                Path<Boolean> latestField = root.get("latest");

                List<Predicate> list = new ArrayList<Predicate>();

                // 模糊搜素
                if(StrUtil.isNotBlank(actProcess.getName())){
                    list.add(cb.like(nameField,'%'+actProcess.getName()+'%'));
                }
                if(StrUtil.isNotBlank(actProcess.getProcessKey())){
                    list.add(cb.like(processKeyField,'%'+actProcess.getProcessKey()+'%'));
                }

                // 分类
                if(StrUtil.isNotBlank(actProcess.getCategoryId())){
                    list.add(cb.equal(categoryIdField, actProcess.getCategoryId()));
                }

                // 状态
                if(actProcess.getStatus()!=null){
                    list.add(cb.equal(statusField, actProcess.getStatus()));
                }
                // 创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                // 只显示最新
                if(showLatest!=null&&showLatest){
                    list.add(cb.equal(latestField, true));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public ActProcess findByProcessKeyAndLatest(String processKey, Boolean latest) {

        List<ActProcess> list = actProcessDao.findByProcessKeyAndLatest(processKey, latest);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void setAllOldByProcessKey(String processKey) {

        List<ActProcess> list = actProcessDao.findByProcessKey(processKey);
        if(list==null||list.size()==0){
            return;
        }
        list.forEach(item -> {
            item.setLatest(false);
        });
        actProcessDao.saveAll(list);
    }

    @Override
    public void setLatestByProcessKey(String processKey) {

        ActProcess actProcess = actProcessDao.findTopByProcessKeyOrderByVersionDesc(processKey);
        if(actProcess==null){
            return;
        }
        actProcess.setLatest(true);
        actProcessDao.save(actProcess);
    }

    @Override
    public List<ActProcess> findByCategoryId(String categoryId) {

        return actProcessDao.findByCategoryId(categoryId);
    }

    @Override
    public String startProcess(ActBusiness actBusiness) {

        String userId = securityUtil.getCurrUser().getId();
        // 启动流程用户
        identityService.setAuthenticatedUserId(userId);
        // 启动流程 只需传入业务表id变量
        Map<String, Object> paramId = new HashMap<>(16);
        paramId.put("tableId", actBusiness.getTableId());
        ProcessInstance pi = runtimeService.startProcessInstanceById(actBusiness.getProcDefId(), actBusiness.getId(), paramId);
        // 设置流程实例名称
        runtimeService.setProcessInstanceName(pi.getId(), actBusiness.getTitle());
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for(Task task : tasks){
            // 分配第一个任务用户
            for(String assignee : actBusiness.getAssignees()){
                taskService.addCandidateUser(task.getId(), assignee);
                // 异步发消息
                messageUtil.sendActMessage(assignee, ActivitiConstant.MESSAGE_TODO_CONTENT, actBusiness.getSendMessage(),
                        actBusiness.getSendSms(), actBusiness.getSendEmail());
            }
            // 设置任务优先级
            taskService.setPriority(task.getId(), actBusiness.getPriority());
        }
        return pi.getId();
    }

    @Override
    public ProcessNodeVo getFirstNode(String procDefId) {

        BpmnModel bpmnModel = repositoryService.getBpmnModel(procDefId);

        ProcessNodeVo node = new ProcessNodeVo();

        List<Process> processes = bpmnModel.getProcesses();
        Collection<FlowElement> elements = processes.get(0).getFlowElements();
        // 流程开始节点
        StartEvent startEvent = null;
        for (FlowElement element : elements) {
            if (element instanceof StartEvent) {
                startEvent = (StartEvent) element;
                break;
            }
        }
        FlowElement e = null;
        // 判断开始后的流向节点
        SequenceFlow sequenceFlow = startEvent.getOutgoingFlows().get(0);
        for (FlowElement element : elements) {
            if(element.getId().equals(sequenceFlow.getTargetRef())){
                if(element instanceof UserTask){
                    e = element;
                    node.setType(ActivitiConstant.NODE_TYPE_TASK);
                    break;
                }else{
                    throw new SensException("流程设计错误，开始节点后只能是用户任务节点");
                }
            }
        }
        node.setTitle(e.getName());
        // 设置关联用户
        List<User> users = actNodeService.findUserByNodeId(e.getId());
        // 设置关联角色的用户
        List<Role> roles = actNodeService.findRoleByNodeId(e.getId());
        for(Role r : roles){
            List<User> userList = userRoleService.findUserByRoleId(r.getId());
            users.addAll(userList);
        }
        // 设置关联部门负责人
        List<String> departmentIds = actNodeService.findDepartmentIdsByNodeId(e.getId());
        List<DepartmentHeader> departments = departmentHeaderService.findByDepartmentIdIn(departmentIds);
        for (DepartmentHeader d : departments){
            User user = userService.get(d.getUserId());
            users.add(user);
        }
        node.setUsers(removeDuplicate(users));
        return node;
    }

    @Override
    public ProcessNodeVo getNextNode(String procInstId) {

        ProcessNodeVo node = new ProcessNodeVo();

        // 当前执行节点id
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        String currActId = pi.getActivityId();
        ProcessDefinitionEntity dfe = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(pi.getProcessDefinitionId());
        // 获取所有节点
        List<ActivityImpl> activitiList = dfe.getActivities();
        // 判断出当前流程所处节点，根据路径获得下一个节点实例
        for(ActivityImpl activityImpl : activitiList){
            if (activityImpl.getId().equals(currActId)) {
                // 获取下一个节点
                List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();

                PvmActivity pvmActivity = pvmTransitions.get(0).getDestination();
                String type = pvmActivity.getProperty("type").toString();
                if("userTask".equals(type)){
                    // 用户任务节点
                    node.setType(ActivitiConstant.NODE_TYPE_TASK);
                    node.setTitle(pvmActivity.getProperty("name").toString());
                    // 设置关联用户
                    List<User> users = actNodeService.findUserByNodeId(pvmActivity.getId());
                    // 设置关联角色的用户
                    List<Role> roles = actNodeService.findRoleByNodeId(pvmActivity.getId());
                    for(Role r : roles){
                        List<User> userList = userRoleService.findUserByRoleId(r.getId());
                        users.addAll(userList);
                    }
                    // 设置关联部门负责人
                    List<String> departmentIds = actNodeService.findDepartmentIdsByNodeId(pvmActivity.getId());
                    List<DepartmentHeader> departments = departmentHeaderService.findByDepartmentIdIn(departmentIds);
                    for (DepartmentHeader d : departments){
                        User user = userService.get(d.getUserId());
                        users.add(user);
                    }
                    node.setUsers(removeDuplicate(users));
                }else if("endEvent".equals(type)){
                    // 结束
                    node.setType(ActivitiConstant.NODE_TYPE_END);
                }else{
                    throw new SensException("流程设计错误，无法处理的节点");
                }
                break;
            }
        }

        return node;
    }

    @Override
    public ProcessNodeVo getNode(String nodeId) {

        ProcessNodeVo node = new ProcessNodeVo();
        // 设置关联用户
        List<User> users = actNodeService.findUserByNodeId(nodeId);
        // 设置关联角色的用户
        List<Role> roles = actNodeService.findRoleByNodeId(nodeId);
        for(Role r : roles){
            List<User> userList = userRoleService.findUserByRoleId(r.getId());
            users.addAll(userList);
        }
        // 设置关联部门负责人
        List<String> departmentIds = actNodeService.findDepartmentIdsByNodeId(nodeId);
        List<DepartmentHeader> departments = departmentHeaderService.findByDepartmentIdIn(departmentIds);
        for (DepartmentHeader d : departments){
            User user = userService.get(d.getUserId());
            users.add(user);
        }
        node.setUsers(removeDuplicate(users));
        return node;
    }

    /**
     * 去重
     * @param list
     * @return
     */
    private List<User> removeDuplicate(List<User> list) {

        LinkedHashSet<User> set = new LinkedHashSet<User>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}