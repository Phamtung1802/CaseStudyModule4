package repositories;

import com.example.demo.models.AppUser;
import org.apache.catalina.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    public Iterable<AppUser> findAll();
}