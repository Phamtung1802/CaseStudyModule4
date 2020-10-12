package repositories;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface AppPostRepository extends PagingAndSortingRepository<AppPost, Long> {
    public Iterable<AppPost> findAll();
    public Iterable<AppPost> findAllByPostNameContains(String string);
    public void deleteAppPostByPostId(Long id);
    public AppPost findByPostId(Long l);
}