package com.wizard.web.application.manage.permission.bean;

import com.wizard.web.domain.entity.WizardMenu;

public class Menu extends WizardMenu {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8775556285714808247L;

    private String parentMenuName = null;

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
