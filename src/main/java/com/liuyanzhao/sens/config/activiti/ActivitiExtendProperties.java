package com.liuyanzhao.sens.config.activiti;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 言曌
 */
@Data
@ConfigurationProperties(prefix = "spring.activiti.sens")
public class ActivitiExtendProperties {

    /**
     * 流程图字体配置
     */
    private String activityFontName = "宋体";

    /**
     * 流程图字体配置
     */
    private String labelFontName = "宋体";
}
