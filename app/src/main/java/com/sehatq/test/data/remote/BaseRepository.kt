package com.sehatq.test.data.remote

import com.sehatq.test.domain.entities.BaseObjectEntity
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
abstract class BaseRepository<E : BaseObjectEntity?> {
    protected var entities: List<E> = arrayListOf()
    protected var remoteAPI: RemoteAPI = RetrofitFactory.instance!!.remoteAPI

    companion object {
        private val instance: BaseRepository<*>? = null
    }

}