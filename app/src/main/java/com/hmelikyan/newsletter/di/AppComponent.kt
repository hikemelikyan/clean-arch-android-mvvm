package com.hmelikyan.newsletter.di

import android.content.Context
import com.hmelikyan.newsletter.data.di.DataModule
import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.domain.di.DomainModule
import com.hmelikyan.newsletter.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class,DataModule::class,DomainModule::class])
interface AppComponent {
    fun inject(target: MainActivity)

    @Component.Builder
    interface Builder{
        fun withContext(@BindsInstance context: Context):Builder
        fun build():AppComponent
    }
}