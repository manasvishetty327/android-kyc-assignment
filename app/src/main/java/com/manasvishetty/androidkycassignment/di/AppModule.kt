package com.manasvishetty.androidkycassignment.di

import com.manasvishetty.androidkycassignment.data.repository.CustomerRepository
import com.manasvishetty.androidkycassignment.domain.repository.CustomerRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCustomerRepository(): CustomerRepositoryInterface {
        return CustomerRepository()
    }
}