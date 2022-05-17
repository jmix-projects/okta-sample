package com.company.oks.security;

import io.jmix.core.security.InMemoryUserRepository;
import org.springframework.stereotype.Component;

/**
 * In memory user repository is used instead of standard database user repository.
 */
@Component
public class MyUserRepository extends InMemoryUserRepository {

    //Init system and anonymous users here

    /*
    @Autowired
    private ResourceRoleRepository resourceRoleRepository;

    @Override
    protected UserDetails createSystemUser() {
        MyUser systemUser = new MyUser();
        systemUser.setUsername("system");
        systemUser.setAuthorities(getSystemGrantedAuthorities());
        return systemUser;
    }

    private List<GrantedAuthority> getSystemGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        ResourceRole fullAccessRole = resourceRoleRepository.findRoleByCode(FullAccessRole.CODE);
        RoleGrantedAuthority roleGrantedAuthority = RoleGrantedAuthority.ofResourceRole(fullAccessRole);
        grantedAuthorities.add(roleGrantedAuthority);
        return grantedAuthorities;
    }

    @Override
    protected UserDetails createAnonymousUser() {
        MyUser systemUser = new MyUser();
        systemUser.setUsername("anonymous");
        return systemUser;
    }
    */
}
