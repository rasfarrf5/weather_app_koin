package com.rasfar.koin.app.network

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { providesHttpLoggingInterceptor() }
    factory { providesCache(get()) }
    factory { providesOkHttpClient(get(), get(), get()) }
    factory { providesRetroFit(get(), get()) }
}

fun providesRetroFit(configuration: NetworkConfiguration, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(configuration.getHost())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(
            RxThreadCallAdapter(
                configuration.ioScheduler(),
                configuration.mainThreadScheduler()
            )
        )
        .client(okHttpClient)
        .build()
}

fun providesOkHttpClient(
    interceptor: HttpLoggingInterceptor,
    configuration: NetworkConfiguration,
    cache: Cache
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(configuration.getTimeoutSeconds(), TimeUnit.SECONDS)
        .connectTimeout(configuration.getTimeoutSeconds(), TimeUnit.SECONDS)
        .cache(cache)
        .build()
}

fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun providesCache(configuration: NetworkConfiguration): Cache {
    return Cache(configuration.getCacheDir(), configuration.getCacheSize())
}
