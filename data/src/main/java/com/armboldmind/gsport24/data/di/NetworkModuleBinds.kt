package com.armboldmind.gsport24.data.di

import com.armboldmind.gsport24.data.root.NetworkHelper
import com.armboldmind.gsport24.data.root.NetworkHelperImpl
import com.armboldmind.gsport24.data.root.ResultFactory
import com.armboldmind.gsport24.data.root.ResultFactoryImpl
import com.armboldmind.gsport24.root.di.RootModule
import com.armboldmind.gsport24.root.di.RootModuleBinds
import dagger.Binds
import dagger.Module

@Module(includes = [RootModule::class,RootModuleBinds::class])
interface NetworkModuleBinds {

    @Binds
    fun providesNetworkHelper(helperImpl: NetworkHelperImpl): NetworkHelper

    @Binds
    fun providesResultFactory(resultFactoryImpl: ResultFactoryImpl):ResultFactory

}
