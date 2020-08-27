package com.sehatq.test.ui.detailProduct

import android.content.Intent
import com.sehatq.test.ui.base.BaseNavigator

interface DetailProductNavigator : BaseNavigator {
    fun intentShare(intent: Intent)
    fun successBuy()
}