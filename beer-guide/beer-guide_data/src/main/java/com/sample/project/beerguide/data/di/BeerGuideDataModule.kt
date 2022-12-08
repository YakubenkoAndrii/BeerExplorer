package com.sample.project.beerguide.data.di

import android.content.Context
import androidx.room.Room
import com.sample.project.beerguide.data.local.ProductsDatabase
import com.sample.project.beerguide.data.network.BeerService
import com.sample.project.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BeerGuideDataModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BeerService.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideBeerService(
        retrofit: Retrofit
    ): BeerService {
        return retrofit.create(BeerService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ProductsDatabase::class.java,
        "ProductsDatabase"
    ).build()

    @Singleton
    @Provides
    fun provideProductsDao(
        database: ProductsDatabase
    ) = database.productsDao
}
