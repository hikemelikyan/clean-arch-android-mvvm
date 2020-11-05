package com.hmelikyan.newsletter.domain

import com.hmelikyan.newsletter.domain.data.AuthRepository
import javax.inject.Inject

class Domain @Inject constructor(
    private val authRepository: AuthRepository
) {

    private fun modifyData(count: Float): Float {
        return (count * Math.PI).toFloat()
    }


    fun logIn(): String {
        return authRepository.logIn()
    }

}