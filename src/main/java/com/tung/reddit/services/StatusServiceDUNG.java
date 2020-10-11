package com.tung.reddit.services;

import com.tung.reddit.models.AppStatus;

import java.util.Optional;

public interface StatusServiceDUNG {
    Optional<AppStatus> findByName (String statusName);
}
