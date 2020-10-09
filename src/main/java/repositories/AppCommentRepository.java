package repositories;

import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppCommentRepository extends PagingAndSortingRepository<AppComment, Long> {
    public Iterable<AppComment> findAllByPost(AppPost appPost);
}