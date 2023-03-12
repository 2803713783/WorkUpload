package com.siyi.project.system.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.siyi.common.utils.StringUtils;
import com.siyi.framework.config.SiYiConfig;

/**
 * 首页
 *
 * @author siyi
 */
@RestController
public class SysIndexController
{
    /** 系统基础配置 */
    @Resource
    private SiYiConfig SiYiConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index()
    {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", SiYiConfig.getName(), SiYiConfig.getVersion());
    }
}
