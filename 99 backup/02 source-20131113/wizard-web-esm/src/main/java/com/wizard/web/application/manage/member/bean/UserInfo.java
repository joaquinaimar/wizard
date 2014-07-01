package com.wizard.web.application.manage.member.bean;

import com.wizard.web.domain.entity.WizardUserInfo;

public class UserInfo extends WizardUserInfo {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2579753284878453924L;

    private String newPassword = null;

    private String roleName = null;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
