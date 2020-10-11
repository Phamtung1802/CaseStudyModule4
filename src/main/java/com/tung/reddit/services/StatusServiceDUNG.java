package com.tung.reddit.services;

import com.tung.reddit.models.Status;

import java.util.Optional;

public interface StatusServiceDUNG {
    Optional<Status> findByName (String statusName);
}
