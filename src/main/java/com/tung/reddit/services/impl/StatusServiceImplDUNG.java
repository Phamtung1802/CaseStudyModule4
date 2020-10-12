package com.tung.reddit.services.impl;

import com.tung.reddit.repository.StatusRepositoryDUNG;
import com.tung.reddit.services.StatusServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class StatusServiceImplDUNG implements StatusServiceDUNG {

    @Autowired
    private StatusRepositoryDUNG statusRepository;


    @Override
    public Optional<AppStatus> findByName(String statusName) {
        return statusRepository.findByName(statusName);
    }
}
