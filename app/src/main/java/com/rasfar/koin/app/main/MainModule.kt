package com.rasfar.koin.app.main

import com.rasfar.koin.app.service.WeatherApiComponent
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val mainModule = module {
    single { WeatherApiComponent() }
    factory { providesMainPresenter(get(), get()) }
}

fun providesMainPresenter(
    weatherApiComponent: WeatherApiComponent,
    disposable: CompositeDisposable
): MainContract.Presenter {
    return MainPresenter(weatherApiComponent.weatherProvider, disposable)
}