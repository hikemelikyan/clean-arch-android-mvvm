package com.hmelikyan.newsletter.data.di

import com.hmelikyan.newsletter.data.root.NetworkHelper
import com.hmelikyan.newsletter.data.root.NetworkHelperImpl
import com.hmelikyan.newsletter.root.di.RootModule
import com.hmelikyan.newsletter.root.di.RootModuleBinds
import dagger.Binds
import dagger.Module

@Module(includes = [RootModule::class,RootModuleBinds::class])
interface NetworkModuleBinds {

    @Binds
    fun providesNetworkHelper(helperImpl: NetworkHelperImpl): NetworkHelper

}
