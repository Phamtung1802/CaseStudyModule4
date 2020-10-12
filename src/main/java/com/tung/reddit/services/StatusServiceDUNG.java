package com.tung.reddit.services;

import java.util.Optional;

public interface StatusServiceDUNG {
    Optional<AppStatus> findByName (String statusName);
}
