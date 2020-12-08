package com.armboldmind.gsport24.data.root

import java.lang.Exception

class ApiException(val state: UIState, message: String?, cause: Throwable?) : Exception(message, cause)