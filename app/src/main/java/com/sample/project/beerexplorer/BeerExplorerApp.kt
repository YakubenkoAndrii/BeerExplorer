package com.sample.project.beerexplorer

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BeerExplorerApp : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(context = applicationContext)
        .respectCacheHeaders(false)
        .diskCache {
            DiskCache.Builder()
                .directory(applicationContext.cacheDir.resolve("image_cache"))
                .build()
        }.build()
}
