package com.siyi.project.system.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.siyi.common.utils.StringUtils;
import com.siyi.framework.security.RegisterBody;
import com.siyi.framework.security.service.SysRegisterService;
import com.siyi.framework.web.controller.BaseController;
import com.siyi.framework.web.domain.AjaxResult;
import com.siyi.project.system.service.ISysConfigService;

/**
 * 注册验证
 *
 * @author siyi
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Resource
    private SysRegisterService registerService;

    @Resource
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
