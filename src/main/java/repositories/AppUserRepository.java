package repositories;

import com.tung.reddit.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
    public Optional<AppUser> findByUsername(String username);
    public Iterable<AppUser> findAll();
    public AppUser findByUserId(Long l);
    public AppUser findFirstByUsername(String username);
}