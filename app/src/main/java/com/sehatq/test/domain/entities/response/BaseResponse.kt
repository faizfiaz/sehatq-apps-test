package com.sehatq.test.domain.entities.response

import com.sehatq.test.domain.entities.BaseObjectEntity

open class BaseResponse<E> : BaseObjectEntity() {
    var data: E? = null
    var error: String? = null

}


