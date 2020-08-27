package com.sehatq.test.data.remote;

import com.sehatq.test.domain.entities.requests.LoginRequest;
import com.sehatq.test.domain.entities.response.ResponseDataHome;
import com.sehatq.test.domain.entities.response.UserResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.observers.TestObserver;
import retrofit2.HttpException;

public class UserRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock RemoteAPI remoteAPI;

    @InjectMocks
    UserRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoginSuccessful() {
        LoginRequest request = new LoginRequest("eve.holt@reqres.in", "1234");
        TestObserver<UserResponse> observer = repository.login(request).test();
        observer.awaitTerminalEvent();
        observer.assertNoErrors().assertValue(r -> r.getToken() != null);
    }

    @Test
    public void testLoginUnauthorized() {
        LoginRequest request = new LoginRequest("eve.holt@reqres.inssss", "1234");
        TestObserver<UserResponse> observer = repository.login(request).test();
        observer.awaitTerminalEvent();
        observer.assertError(error -> error instanceof HttpException);
    }

    @Test
    public void testGetDataSuccessful() {
        TestObserver<List<ResponseDataHome>> observer = repository.homeData().test();
        observer.awaitTerminalEvent();
        observer.assertNoErrors().assertValue(r -> (r.size() > 0));
    }
}