package com.armboldmind.gsport24.di

import com.armboldmind.gsport24.Application
import com.armboldmind.gsport24.data.di.NetworkModule
import com.armboldmind.gsport24.data.di.NetworkModuleBinds
import com.armboldmind.gsport24.domain.di.DomainModuleRepositoryBinds
import com.armboldmind.gsport24.root.di.RootModule
import com.armboldmind.gsport24.root.di.RootModuleBinds
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