package com.tung.reddit.services;

import com.tung.reddit.models.AppRole;

public interface AppRoleServiceDUNG {
    Iterable<AppRole> getAllRole();
    AppRole getRoleById(Long id);
    AppRole save(AppRole role);
    void remove(AppRole role);
    AppRole getRoleByName(String roleName);
}
