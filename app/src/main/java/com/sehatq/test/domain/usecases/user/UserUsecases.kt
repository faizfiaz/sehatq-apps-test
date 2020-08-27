package com.sehatq.test.domain.usecases.user

import com.sehatq.test.data.local.PreferencesManager
import com.sehatq.test.data.remote.UserRepository
import com.sehatq.test.domain.entities.TripsEntity
import com.sehatq.test.domain.entities.requests.LoginRequest
import com.sehatq.test.domain.entities.response.ResponseDataHome
import com.sehatq.test.domain.entities.response.UserResponse
import com.sehatq.test.domain.mappers.UserMapper
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

open class UserUsecases(mapper: UserMapper, repository: UserRepository?) : IUserUsecases(mapper, repository) {

    var preferencesManager: PreferencesManager? = PreferencesManager.instance

    override suspend fun login(identifier: String, password: String): Single<Any?> = withContext(Dispatchers.IO) {
        var loginRequest = LoginRequest(identifier, password)
        val response = async { repository?.login(loginRequest) }
        try {
            checkResponse(response.await()?.blockingGet())
        } catch (e: Exception) {
            throw java.lang.Exception(e.message)
        }
    }

    override fun checkToken(): Boolean {
        return !preferencesManager?.prefToken.isNullOrBlank()
    }

    override suspend fun getHomeData(): Single<Any?> = withContext(Dispatchers.IO) {
        val response = async { repository?.homeData() }
        try {
            checkResponse(response.await()?.blockingGet())
        } catch (e: Exception) {
            throw java.lang.Exception(e.message)
        }

    }


    private fun checkResponse(response: List<ResponseDataHome>?): Single<Any?> {
        if (response == null) {
            return Single.just("Something wrong")
        }
        val responseMapping = mapper.convertToObjectList(response)
        return Single.just(responseMapping)
    }

    fun checkResponse(response: UserResponse?): Single<Any?> {
        if (response?.error != null) {
            return Single.just(response.message)
        }
        preferencesManager?.prefToken = response?.token
        return Single.just(true)
    }

}