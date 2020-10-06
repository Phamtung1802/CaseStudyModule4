package com.tung.reddit;

import com.tung.reddit.services.AppUserService;
import com.tung.reddit.services.AppUserServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.AppUserRepository;

@Configuration
public class AppUserServiceTestConFig {
    @Bean
    public AppUserRepository appUserRepository() {
        return Mockito.mock(AppUserRepository.class);
    }

    @Bean
    public AppUserService customerService() {
        return new AppUserServiceImpl();
    }
}

