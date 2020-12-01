package com.hmelikyan.newsletter.shared.util

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun permissionChecker(action : PermissionChecker.() -> Unit) {
	PermissionChecker.apply { action(this) }
}

object PermissionChecker {

	private var fm : FragmentManager? = null
	private lateinit var permissions : Array<out String>

	fun withActivity(activity : AppCompatActivity) {
		fm = activity.supportFragmentManager
	}

	fun withFragment(fragment : Fragment) {
		fm = fragment.childFragmentManager
	}

	fun permissions(vararg permissions : String) {
		this.permissions = permissions
	}

	fun check(callback : PermissionResultChecker.() -> Unit) {
		val fragment = PermissionCheckerFragment.newInstance()
		fm?.beginTransaction()
			?.add(fragment, PermissionCheckerFragment::class.java.simpleName)
			?.commitNow() ?: throw IllegalStateException("For correct working you need to pass Fragment or FragmentActivity")
		fragment.requestPermissions(permissions, PermissionResultChecker.apply(callback).getCallback()) {
			fm?.beginTransaction()?.remove(fragment)
		}
	}

	class PermissionCheckerFragment : Fragment() {

		private lateinit var resultHandler : PermissionResultChecker.Callback
		private var onFinishRequest : (() -> Unit)? = null

		companion object {

			private const val PERMISSIONS_REQUEST_CODE = 74

			fun newInstance() = PermissionCheckerFragment()

		}

		fun requestPermissions(permissions : Array<out String>, resultHandler : PermissionResultChecker.Callback, onFinishRequest : () -> Unit) {
			this.resultHandler = resultHandler
			this.onFinishRequest = onFinishRequest
			requestPermissions(permissions, PERMISSIONS_REQUEST_CODE)
		}

		override fun onRequestPermissionsResult(requestCode : Int, permissions : Array<out String>, grantResults : IntArray) {
			if (requestCode == PERMISSIONS_REQUEST_CODE) {
				permissions.forEachIndexed { index, permission ->
					if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
						resultHandler.permissionGranted(permission)
					} else {
						if (shouldShowRequestPermissionRationale(permission)) {
							resultHandler.permissionDenied(permission)
						} else {
							resultHandler.permissionDeniedWithNeverAsk(permission)
						}
					}
				}
				onFinishRequest?.invoke()
			}
		}
	}

	object PermissionResultChecker {

		private lateinit var onSuccess : (granted : String) -> Unit
		private lateinit var onFailure : (granted : String) -> Unit
		private lateinit var onFailureWithNeverAsk : (granted : String) -> Unit

		fun getCallback() : Callback {
			return object : Callback {
				override fun permissionGranted(granted : String) {
					onSuccess(granted)
				}

				override fun permissionDenied(denied : String) {
					onFailure(denied)
				}

				override fun permissionDeniedWithNeverAsk(denied : String) {
					onFailureWithNeverAsk(denied)
				}
			}
		}

		interface Callback {

			fun permissionGranted(granted : String)
			fun permissionDenied(denied : String)
			fun permissionDeniedWithNeverAsk(denied : String)
		}

		fun doOnSuccess(block : (granted : String) -> Unit) = apply { onSuccess = block }

		fun doOnFailure(block : (denied : String) -> Unit) = apply { onFailure = block }

		fun doOnNeverAsk(block : (denied : String) -> Unit) = apply { onFailureWithNeverAsk = block }

	}

}