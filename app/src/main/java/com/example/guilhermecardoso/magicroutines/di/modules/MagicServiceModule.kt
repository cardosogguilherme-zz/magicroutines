package com.example.guilhermecardoso.magicroutines.di.modules

import com.example.guilhermecardoso.magicroutines.service.MagicAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class MagicServiceModule {

    @Provides
    fun providesMagicService(retrofit: Retrofit): MagicAPI =
            retrofit.create(MagicAPI::class.java)

}