package com.tung.reddit.repository;

import com.tung.reddit.models.AppStatus;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface StatusRepositoryDUNG extends CrudRepository<AppStatus, Long> {
    Optional <AppStatus> findByName(String statusName);
}
