package com.tung.reddit.services;

import com.tung.reddit.models.AppRole;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component

public interface AppRoleServiceD {
    Iterable<AppRole> getAllRole();

    AppRole getRoleById(Long id);

    AppRole save(AppRole appRole);

    void remove(AppRole appRole);

    AppRole getRoleByName(String roleName);
}
