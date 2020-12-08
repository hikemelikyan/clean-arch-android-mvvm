package com.armboldmind.gsport24.shared.ext

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import com.armboldmind.gsport24.R

fun View.validationError(predicate : (String) -> Boolean) {
	setBackgroundResource(R.drawable.bcg_sign_in_edit_text_bordered_error)
	if (this is AppCompatEditText) {
		doAfterTextChanged {
			if (predicate(it.toString())) {
				setBackgroundResource(R.drawable.bcg_sign_in_edit_text_bordered)
			} else {
				setBackgroundResource(R.drawable.bcg_sign_in_edit_text_bordered_error)
			}
		}
	}
	if (this is TextView) {
		doAfterTextChanged {
			if (predicate(it.toString())) {
				setBackgroundResource(R.drawable.bcg_sign_in_edit_text_bordered)
			} else {
				setBackgroundResource(R.drawable.bcg_sign_in_edit_text_bordered_error)
			}
		}
	}
}