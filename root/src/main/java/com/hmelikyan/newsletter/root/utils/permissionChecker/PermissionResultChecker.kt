package com.hmelikyan.newsletter.root.utils.permissionChecker

interface PermissionResultChecker{
	fun doOnSuccess(block : (granted : String) -> Unit)
	fun doOnFailure(block : (denied : String) -> Unit)
	fun doOnNeverAsk(block : (denied : String) -> Unit)
}