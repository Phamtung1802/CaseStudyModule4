package repositories;

import com.example.demo.models.AppRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppRoleRepository extends PagingAndSortingRepository<AppRole, Long> {
    public Iterable<AppRole> findAll();
}