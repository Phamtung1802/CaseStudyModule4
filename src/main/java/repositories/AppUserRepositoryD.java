package repositories;

import com.tung.reddit.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Component

public interface AppUserRepositoryD extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findAppUsersByUsername(String username);

}