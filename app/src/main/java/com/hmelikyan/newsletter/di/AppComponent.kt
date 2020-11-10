package com.hmelikyan.newsletter.di

import com.hmelikyan.newsletter.Application
import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.domain.di.DomainModule
import com.hmelikyan.newsletter.root.di.RootModuleBinds
import dagger.Component
import dagger.hilt.android.internal.modules.ApplicationContextModule

@Component(
    modules = [
        ApplicationContextModule::class,
        NetworkModule::class,
        DomainModule::class,
        RootModuleBinds::class
    ]
)
interface AppComponent {
    fun inject(target: Application)

    @Component.Builder
    interface Builder {
        fun withAppModule(module:ApplicationContextModule):Builder
        fun build():AppComponent
    }
}