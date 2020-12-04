package com.hmelikyan.newsletter.domain.mappers

import com.hmelikyan.newsletter.domain.entities.SmsCodeIdDomain
import com.hmelikyan.newsletter.root.mapperBase.Mapper

object VerificationCodeIdMapper:Mapper<Int?,SmsCodeIdDomain> {

	override fun invoke(input : Int?) : SmsCodeIdDomain {
		return SmsCodeIdDomain(input ?: 0)
	}
}