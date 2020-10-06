package com.tung.reddit;

import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import repositories.AppUserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitJupiterConfig(AppUserServiceTestConFig.class)
public class AppUserServiceTest {
    @Autowired
    private AppUserService appUserServiceTest;

    @Autowired
    private AppUserRepository appUserRepositoryTest;

    @AfterEach
    private void resetMocks() {
        Mockito.reset(appUserRepositoryTest);
    }

//    @Test
//    void testFindAll() {
//        List<AppUser> customers = new ArrayList<>();
//        customers.add(new AppUser());
//        Pageable pageInfo = new PageRequest.of(0,25);
//        Page<AppUser> customerPage = new PageImpl<AppUser>(customers, pageInfo, 1);
//        when(appUserRepositoryImpl.findAll(pageInfo)).thenReturn(customerPage);
//
//        Page<AppUser> actual = (Page<AppUser>) appUserServiceImpl.findAll();
//        verify(appUserRepositoryImpl).findAll(pageInfo);
//        assertEquals(customerPage, actual);
//    }

    @Test
    void testFindOneNotFound() {
        when(appUserRepositoryTest.findById(1L)).thenReturn(null);
        AppUser actual = appUserServiceTest.findByUserId(1L);
        verify(appUserServiceTest).findByUserId(1L);
        assertNull(actual);
    }
    @Test
    void saveCustomer() {
        AppUser customer = new AppUser();
        appUserServiceTest.save(customer);
        verify(appUserRepositoryTest).save(customer);
    }
}