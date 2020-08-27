package com.sehatq.test.domain.entities.response

import com.google.gson.annotations.SerializedName

class UserResponse(
        @SerializedName("token") val token: String? = null
) : BaseResponse<Any>()