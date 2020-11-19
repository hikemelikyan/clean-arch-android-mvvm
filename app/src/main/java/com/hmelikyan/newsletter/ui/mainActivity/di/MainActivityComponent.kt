package com.hmelikyan.newsletter.ui.mainActivity.di

import com.hmelikyan.newsletter.ui.mainActivity.MainViewModel
import dagger.Component

@Component(
    modules = [MainActivityBinds::class]
)
interface MainActivityComponent {

    fun inject(target: MainViewModel)

}
