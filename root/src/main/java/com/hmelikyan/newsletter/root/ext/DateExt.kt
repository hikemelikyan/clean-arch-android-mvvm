package com.hmelikyan.newsletter.root.ext

import com.hmelikyan.newsletter.root.utils.DatePatterns.UTC
import java.text.SimpleDateFormat
import java.util.*

fun Date.toServerDate(pattern : String = UTC) : String {
	val sdf = SimpleDateFormat(pattern, Locale.getDefault())
	sdf.timeZone = TimeZone.getTimeZone("UTC")
	return try {
		sdf.format(this)
	} catch (e : Throwable) {
		e.printStackTrace()
		""
	}
}

fun String?.convertFrom(pattern : () -> String) : Date {
	val sdf = SimpleDateFormat(pattern(), Locale.getDefault())
	sdf.timeZone = TimeZone.getTimeZone("UTC")
	return if (this != null) {
		sdf.parse(this)?.let {
			it
		} ?: throw Exception("Date $this is not valid")
	} else
		throw Exception("Date $this is not valid")
}

fun Date.convertTo(pattern : () -> String) : String {
	val sdf = SimpleDateFormat(pattern(), Locale.getDefault())
	return try {
		sdf.format(this)
	} catch (e : Throwable) {
		e.printStackTrace()
		""
	}
}