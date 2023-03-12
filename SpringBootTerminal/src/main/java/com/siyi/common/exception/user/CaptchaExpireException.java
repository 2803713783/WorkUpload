package com.siyi.common.exception.user;

/**
 * 验证码失效异常类
 *
 * @author siyi
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}
