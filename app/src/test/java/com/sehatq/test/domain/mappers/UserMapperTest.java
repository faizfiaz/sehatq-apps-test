package com.sehatq.test.domain.mappers;

import com.sehatq.test.domain.entities.response.ResponseDataHome;
import com.sehatq.test.domain.models.DataHome;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class UserMapperTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks UserMapper userMapper;

    private ResponseDataHome responseDataHome;
    private DataHome dataHome;

    @Before
    public void setUp() {
        responseDataHome = new ResponseDataHome();

        dataHome = new DataHome();
    }

    @Test
    public void createObjectValid() {
        assertNotNull(userMapper.createObject());
    }

    @Test
    public void createEntityValid() {
        assertNotNull(userMapper.createEntity());
    }

    @Test
    public void defineObjectValid() {
        assertSame(dataHome, userMapper.defineObject(dataHome));
    }

    @Test
    public void defineEntityValid() {
        assertSame(responseDataHome, userMapper.defineEntity(responseDataHome));
    }
}
