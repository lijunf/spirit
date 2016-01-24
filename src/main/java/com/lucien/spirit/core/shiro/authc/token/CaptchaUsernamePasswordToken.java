package com.lucien.spirit.core.shiro.authc.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -4746028009681958929L;

    private String kaptcha;

    public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host,
            String kaptcha) {
        super(username, password, rememberMe, host);
        this.kaptcha = kaptcha;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

}
