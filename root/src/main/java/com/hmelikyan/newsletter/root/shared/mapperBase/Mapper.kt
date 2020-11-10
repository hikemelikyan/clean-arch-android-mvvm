package com.hmelikyan.newsletter.root.shared.mapperBase

interface Mapper<T, R> {

    suspend operator fun invoke(input: T): R

}