package repositories;

import com.tung.reddit.models.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppRoleRepositoryD extends CrudRepository<AppRole, Long> {
    AppRole getAppRoleByName(String roleName);
}

