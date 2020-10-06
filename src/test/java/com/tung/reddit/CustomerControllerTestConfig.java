package com.tung.reddit;

import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppRoleServiceImpl;
import com.tung.reddit.services.AppUserService;
import com.tung.reddit.services.AppUserServiceImpl;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.tung.reddit")
public class CustomerControllerTestConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("tung")
                .build();
    }

    @Bean
    public AppUserService appUserService() {
        return Mockito.mock(AppUserServiceImpl.class);
    }

    @Bean
    public AppRoleService provinceService() {
        return Mockito.mock(AppRoleServiceImpl.class);
    }
}

