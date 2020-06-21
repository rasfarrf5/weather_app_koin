package com.rasfar.koin.app

import com.rasfar.koin.app.network.NetworkConfiguration
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { CompositeDisposable() }

    single { NetworkConfigurationImpl(androidContext()) as NetworkConfiguration }
}