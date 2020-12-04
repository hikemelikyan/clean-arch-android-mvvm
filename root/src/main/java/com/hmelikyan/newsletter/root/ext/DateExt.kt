package com.hmelikyan.newsletter.root.ext

import java.text.SimpleDateFormat
import java.util.*

private const val UTC = "yyyy-MM-dd'T'HH:mm:ss"

fun Date.toServerDate(pattern: String = UTC): String {
	val sdf = SimpleDateFormat(pattern,Locale.getDefault())
	sdf.timeZone = TimeZone.getTimeZone("UTC")
	return try {
		sdf.format(this)
	} catch (e: Throwable) {
		e.printStackTrace()
		""
	}
}