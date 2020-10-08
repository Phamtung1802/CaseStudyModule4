package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.services.AppRoleServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.AppRoleRepositoryDUNG;

@Service
@Component
public class AppRoleServiceImplDUNG implements AppRoleServiceDUNG {

    @Autowired
    private AppRoleRepositoryDUNG appRoleRepositoryDUNG;

    @Override
    public Iterable<AppRole> getAllRole() {
        return appRoleRepositoryDUNG.findAll();
    }

    @Override
    public AppRole getRoleById(Long id) {
        return appRoleRepositoryDUNG.findById(id).orElse(null);
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepositoryDUNG.save(role);
    }

    @Override
    public void remove(AppRole role) {
        appRoleRepositoryDUNG.delete(role);
    }

    @Override
    public AppRole getRoleByName(String roleName) {
        return appRoleRepositoryDUNG.getAppRoleByName(roleName);
    }
}
