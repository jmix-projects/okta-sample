package com.company.oks.oidc;

import io.jmix.multitenancy.core.AcceptsTenant;
import io.jmix.oidc.user.DefaultJmixOidcUser;

/**
 * In-memory Jmix user that supports multitenancy
 */
public class MyUser extends DefaultJmixOidcUser implements AcceptsTenant {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String tenantId;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}