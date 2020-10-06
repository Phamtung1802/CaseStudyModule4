package com.example.demo;

import com.example.demo.models.AppRole;
import com.example.demo.services.AppRoleService;
import com.example.demo.services.AppRoleServiceImpl;
import com.example.demo.services.AppUserService;
import com.example.demo.services.AppUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import repositories.AppRoleRepository;

import java.util.Optional;

@Configuration
@EnableJpaRepositories("repositories")
@ComponentScan()
public class AppConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
