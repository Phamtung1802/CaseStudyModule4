package com.tung.reddit;

import com.tung.reddit.controllers.AppUserController.AppUserController;
import com.tung.reddit.controllers.IndexController;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppUserService;
import com.tung.reddit.services.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringJUnitJupiterConfig(CustomerControllerTestConfig.class)
public class CustomerControllerTest {
    @Autowired
    AppUserService appUserServiceImpl;


    private MockMvc mockMvc;

    @InjectMocks
    private IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(indexController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }


    @Test
    void customersListPageIsExists() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(200));
    }
    @Test
    void customerBrowseControlling() throws Exception {
        mockMvc
                .perform(get("/"))
                .andExpect(status().is(200))
                .andExpect(view().name("/index"));
    }
    @Test
    void customerUpdateSuccessControlling() throws Exception {
        AppUser foo = new AppUser();
    }
}

