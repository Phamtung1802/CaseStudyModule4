package com.tung.reddit.repository;

import com.tung.reddit.models.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepositoryDUNG extends CrudRepository<Status, Long> {
    Optional<Status> findByName(String statusName);
}
