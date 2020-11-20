package com.hmelikyan.newsletter.root.shared.mapperBase

interface Mapper<T, R> {

    operator fun invoke(input: T): R

}