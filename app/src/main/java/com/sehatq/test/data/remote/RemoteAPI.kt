package com.sehatq.test.data.remote

import com.sehatq.test.domain.entities.TripsEntity
import com.sehatq.test.domain.entities.requests.AddTripRequest
import com.sehatq.test.domain.entities.requests.LoginRequest
import com.sehatq.test.domain.entities.response.ResponseDataHome
import com.sehatq.test.domain.entities.response.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * API Backend Service
 */
interface RemoteAPI {
    /******************************* USER **************************/
    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Single<UserResponse?>

    @GET
    fun homeData(@Url url: String): Single<List<ResponseDataHome>>

    @POST
    fun trips(@Url url: String, @Body addTripRequest: AddTripRequest): Single<TripsEntity?>

}