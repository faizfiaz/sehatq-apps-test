package com.sehatq.test.domain.usecases

import com.sehatq.test.data.remote.UserRepository
import com.sehatq.test.domain.mappers.UserMapper
import com.sehatq.test.domain.usecases.user.UserUsecases
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit

class UserUsecasesTest {
    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()
    var userUsecases: UserUsecases? = null

    @InjectMocks
    var userMapper: UserMapper? = null

    @InjectMocks
    var userRepository: UserRepository? = null

    @Before
    fun setUp() {
        userUsecases = UserUsecases(userMapper!!, userRepository)
    }

    @Test
    fun testGetData() {
        runBlocking {
            val testObserver = userUsecases!!.getHomeData().test()
            testObserver.assertComplete()
            Assert.assertTrue(testObserver.values() is List<*>)
        }

    }
}