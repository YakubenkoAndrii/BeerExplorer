package com.sample.project.beerguide.data.di

import com.sample.project.beerguide.data.repository.ProductsRepositoryImpl
import com.sample.project.beerguide.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindProductsRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}
