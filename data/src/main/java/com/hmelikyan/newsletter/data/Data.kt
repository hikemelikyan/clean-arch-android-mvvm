package com.hmelikyan.newsletter.data

import com.hmelikyan.newsletter.root.Root

class Data {

    private val root:Root = Root()

    fun getRootValue(): Int{
        val get1 = root.testProperty
        val get2 = root.testProperty
        val get3 = root.testProperty
        val get4 = root.testProperty
        val get5 = root.testProperty
        val get6 = root.testProperty
        return root.getCount
    }

}