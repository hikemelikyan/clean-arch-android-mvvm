package com.hmelikyan.newsletter.di

import com.hmelikyan.newsletter.Application
import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.data.di.NetworkModuleBinds
import com.hmelikyan.newsletter.domain.di.DomainModuleRepositoryBinds
import com.hmelikyan.newsletter.root.di.RootModule
import com.hmelikyan.newsletter.root.di.RootModuleBinds
import dagger.Component

@Component(
    modules = [
        RootModule::class,
        RootModuleBinds::class,
        NetworkModule::class,
        NetworkModuleBinds::class,
        DomainModuleRepositoryBinds::class,
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun withNetworkModule(networkModule: NetworkModule): Builder
        fun withRootModule(rootModule: RootModule): Builder
        fun build(): AppComponent
    }

    fun inject(target: Application)
}