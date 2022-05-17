package com.company.oks.oidc;

import io.jmix.oidc.claimsmapper.ClaimsRolesMapper;
import io.jmix.oidc.usermapper.BaseOidcUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyOidcUserMapper extends BaseOidcUserMapper<MyUser> {

    private ClaimsRolesMapper claimsRolesMapper;

    public MyOidcUserMapper(ClaimsRolesMapper claimsRolesMapper) {
        this.claimsRolesMapper = claimsRolesMapper;
    }

    @Override
    protected MyUser initJmixUser(OidcUser oidcUser) {
        return new MyUser();
    }

    @Override
    protected void populateUserAttributes(OidcUser oidcUser, MyUser jmixUser) {
        jmixUser.setUsername(oidcUser.getName());
        jmixUser.setFirstName(oidcUser.getGivenName());
        jmixUser.setLastName(oidcUser.getFamilyName());
        jmixUser.setEmail(oidcUser.getEmail());

        //use organization as tenant id
        String organization = (String) oidcUser.getClaims().get("organization");
        jmixUser.setTenantId(organization);
    }

    @Override
    protected void populateUserAuthorities(OidcUser oidcUser, MyUser jmixUser) {
        Collection<? extends GrantedAuthority> authorities = claimsRolesMapper.toGrantedAuthorities(oidcUser.getClaims());
        jmixUser.setAuthorities(authorities);
    }
}