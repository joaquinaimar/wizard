package com.wizard.web.basic.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

public class WizardWebSecurityManager extends DefaultWebSecurityManager {

    public List<Realm> getRealms() {
        return (List<Realm>) super.getRealms();
    }

    public List<AuthorizationInfo> getAuthorizationInfos(
            PrincipalCollection principals) {
        List<AuthorizationInfo> infoList = new ArrayList<AuthorizationInfo>();
        List<Realm> realms = getRealms();
        for (Realm realm : realms) {
            WizardWebRealm webRealm = (WizardWebRealm) realm;
            infoList.add(webRealm.getAuthorizationInfo(principals));
        }
        return infoList;
    }

    public List<String> getRoles(PrincipalCollection principals) {
        List<String> roles = new ArrayList<String>();
        List<AuthorizationInfo> infoList = getAuthorizationInfos(principals);
        for (AuthorizationInfo info : infoList) {
            roles.addAll(info.getRoles());
        }
        return roles;
    }

}
