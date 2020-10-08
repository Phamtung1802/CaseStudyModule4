package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.services.AppRoleServiceD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.AppRoleRepositoryD;

@Service
@Component

public class AppRoleServiceImplD implements AppRoleServiceD {

    @Autowired
    private AppRoleRepositoryD appRoleRepositoryD;

    @Override
    public Iterable<AppRole> getAllRole() {
        return appRoleRepositoryD.findAll();
    }

    @Override
    public AppRole getRoleById(Long id) {
        return appRoleRepositoryD.findById(id).orElse(null);
    }

    @Override
    public AppRole save(AppRole appRole) {
        return appRoleRepositoryD.save(appRole);
    }

    @Override
    public void remove(AppRole appRole) {
        appRoleRepositoryD.delete(appRole);
    }

    @Override
    public AppRole getRoleByName(String roleName) {
        return appRoleRepositoryD.getAppRoleByName(roleName);
    }
}
