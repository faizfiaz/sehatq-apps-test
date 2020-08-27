package com.sehatq.test.di.module

import android.app.Application
import android.content.Context
import com.sehatq.test.App
import com.sehatq.test.R
import com.sehatq.test.data.local.HelperDb
import com.sehatq.test.data.local.dao.DaoProduct
import com.sehatq.test.utils.AndroidUtils
import com.sehatq.test.utils.AppSchedulerProvider
import com.sehatq.test.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath(AndroidUtils.getString(R.string.font_path_poppins))
                .setFontAttrId(R.attr.fontPath)
                .build()
    }

    @Singleton
    @Provides
    fun provideDb(application: Application): HelperDb {
        return HelperDb(application, "sehatq.db")
    }

    @Singleton
    @Provides
    fun provideDaoProduct(): DaoProduct {
        return DaoProduct(App.appContext!!)
    }
}