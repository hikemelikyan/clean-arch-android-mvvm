package com.hmelikyan.newsletter.data

import com.hmelikyan.newsletter.root.Root
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject


class Data {

    private val root:Root = Root()

    @Inject lateinit var mRetrofit:Retrofit

    fun getRootValue(): Int{
        val get1 = root.testProperty
        val get2 = root.testProperty
        val get3 = root.testProperty
        val get4 = root.testProperty
        val get5 = root.testProperty
        val get6 = root.testProperty
        return root.getCount
    }

    fun getRetrofit() = mRetrofit

}