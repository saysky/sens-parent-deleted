package com.liuyanzhao.sens.modules.base.vo;

import lombok.Data;

/**
 * @author 言曌
 */
@Data
public class GithubUserInfo {

    /**
     * 唯一id
     */
    private String id;

    /**
     * 用户名
     */
    private String login;

    /**
     * 头像
     */
    private String avatar_url;
}
