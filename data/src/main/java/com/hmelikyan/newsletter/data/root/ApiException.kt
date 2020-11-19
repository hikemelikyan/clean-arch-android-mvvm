package com.hmelikyan.newsletter.data.root

import java.lang.Exception

class ApiException(val state: UIState, message: String?, cause: Throwable?) : Exception(message, cause)