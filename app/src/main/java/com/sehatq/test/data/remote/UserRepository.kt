package com.sehatq.test.data.remote

import com.sehatq.test.domain.entities.requests.LoginRequest
import com.sehatq.test.domain.entities.response.ResponseDataHome
import com.sehatq.test.domain.entities.response.UserResponse
import io.reactivex.Single

class UserRepository private constructor() : BaseRepository<UserResponse?>() {

    fun login(loginRequest: LoginRequest): Single<UserResponse?>? {
        return remoteAPI.login(loginRequest)
    }

    fun homeData(): Single<List<ResponseDataHome>> {
        return remoteAPI.homeData("https://private-4639ce-ecommerce56.apiary-mock.com/home")
    }

    companion object {
        @JvmStatic
        var instance: UserRepository? = null
            get() {
                if (field == null) {
                    field = UserRepository()
                }
                return field
            }
            private set
    }

}