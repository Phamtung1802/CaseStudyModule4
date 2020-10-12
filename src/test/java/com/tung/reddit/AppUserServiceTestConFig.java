package com.tung.reddit;

import com.tung.reddit.services.AppUserService;
import com.tung.reddit.services.impl.AppUserServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AppUserRepository;

@Configuration
public class AppUserServiceTestConFig {
    @Bean
    public AppUserRepository appUserRepositoryTest() {
        return Mockito.mock(AppUserRepository.class);
    }

    @Bean
    public AppUserService appUserServiceTest() {
        return Mockito.mock(AppUserServiceImpl.class);
    }
}

