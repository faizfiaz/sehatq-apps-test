package com.sehatq.test.domain.usecases.user

import com.sehatq.test.data.remote.UserRepository
import com.sehatq.test.domain.exceptions.MapperException
import com.sehatq.test.domain.mappers.UserMapper
import com.sehatq.test.domain.usecases.base.BaseUsecase
import io.reactivex.Single

abstract class IUserUsecases(mapper: UserMapper, userRepository: UserRepository?) :
        BaseUsecase<UserMapper, UserRepository>(mapper, userRepository) {

    @Throws(MapperException::class)
    abstract suspend fun login(identifier: String, password: String): Single<Any?>

    abstract fun checkToken(): Boolean

    @Throws(MapperException::class)
    abstract suspend fun getHomeData(): Single<Any?>

}