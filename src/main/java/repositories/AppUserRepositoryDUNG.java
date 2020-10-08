package repositories;

import com.tung.reddit.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface AppUserRepositoryDUNG extends CrudRepository<AppUser,Long> {
    Optional <AppUser> findAppUserByUsername(String userName);
}
