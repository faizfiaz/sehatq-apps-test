package com.sehatq.test.domain.usecases.base

import com.sehatq.test.data.remote.BaseRepository
import com.sehatq.test.data.remote.UserRepository
import com.sehatq.test.domain.mappers.BaseMapper

abstract class BaseUsecase<M : BaseMapper<*, *>?, R :
BaseRepository<*>?>(protected var mapper: M, protected var repository: UserRepository?) {

    fun isErrorCode(statusCode: Int): Boolean {
        if (statusCode > 200) {
            return true
        }
        return false
    }
}