package com.hmelikyan.newsletter.domain

import com.hmelikyan.newsletter.data.Data

class Domain() {

    private val data: Data = Data()

    private fun modifyData(count: Float): Float {
        return (count * Math.PI).toFloat()
    }


    fun getData(): Float {
        val getCount = data.getRootValue()
        return modifyData(getCount.toFloat())
    }

}