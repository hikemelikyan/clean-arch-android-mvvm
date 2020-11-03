package com.hmelikyan.newsletter.root

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Root {

    private var _getCount = 0
    val getCount: Int
        get() = _getCount

    private var _testProperty = false
    val testProperty: Boolean by CounterDelegate()


    class CounterDelegate() : ReadWriteProperty<Root, Boolean> {

        private var value: Boolean = false

        override fun getValue(thisRef: Root, property: KProperty<*>): Boolean {
            thisRef._getCount++
            return value
        }

        override fun setValue(thisRef: Root, property: KProperty<*>, value: Boolean) {
            this.value = value
        }
    }

}