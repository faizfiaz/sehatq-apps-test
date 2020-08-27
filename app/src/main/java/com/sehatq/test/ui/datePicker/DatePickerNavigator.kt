package com.sehatq.test.ui.datePicker

import com.sehatq.test.ui.base.BaseNavigator

interface DatePickerNavigator : BaseNavigator {
    fun dismissDialog()
    fun showTimePicker()
    fun sendDateTime(dateTime: String?)
}