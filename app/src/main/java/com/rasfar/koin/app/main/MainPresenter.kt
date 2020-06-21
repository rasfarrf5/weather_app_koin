package com.rasfar.koin.app.main

import com.rasfar.koin.app.service.WeatherProvider
import com.rasfar.koin.app.service.WeatherResult
import io.reactivex.disposables.CompositeDisposable

class MainPresenter (
    private val weatherProvider: WeatherProvider,
    private val compositeDisposable: CompositeDisposable
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    // private val weatherProvider: WeatherProvider by inject(WeatherProvider::class.java)

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun onViewCreated() {
        loadWeatherInfo()
    }

    override fun onWeatherContainerClicked() {
        loadWeatherInfo()
    }

    private fun loadWeatherInfo() {
        val disposable = weatherProvider.getWeatherInfo("Singapore")
            .doOnSubscribe { view.showLoadingSpinner() }
            .doOnTerminate { view.hideLoadingSpinner() }
            .subscribe {
                if (it is WeatherResult.Success) {
                    view.showWeatherInfo(it.weatherData)
                } else {
                    view.showErrorMessage()
                }
            }
        compositeDisposable.add(disposable)
    }

    override fun onViewPaused() {
        compositeDisposable.clear()
    }
}