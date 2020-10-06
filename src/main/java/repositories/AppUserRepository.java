package repositories;

import com.tung.reddit.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    public Iterable<AppUser> findAll();
}