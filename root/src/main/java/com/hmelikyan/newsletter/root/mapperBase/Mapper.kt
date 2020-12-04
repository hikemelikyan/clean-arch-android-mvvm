package com.hmelikyan.newsletter.root.mapperBase

interface Mapper<T, R> {

    operator fun invoke(input: T): R

}