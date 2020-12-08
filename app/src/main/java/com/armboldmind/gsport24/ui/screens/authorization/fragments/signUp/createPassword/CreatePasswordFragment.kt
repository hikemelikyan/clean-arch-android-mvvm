package com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.createPassword

import android.graphics.Typeface
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.armboldmind.gsport24.databinding.FragmentCreatePasswordBinding
import com.armboldmind.gsport24.mvvm.ui.BaseRequestFragment
import com.armboldmind.gsport24.mvvm.vm.ViewCommand
import com.armboldmind.gsport24.root.ext.hide
import com.armboldmind.gsport24.root.ext.show

class CreatePasswordFragment : BaseRequestFragment<FragmentCreatePasswordBinding, CreatePasswordViewModel>() {
    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreatePasswordBinding
        get() = FragmentCreatePasswordBinding::inflate

    override val viewModelType: Class<CreatePasswordViewModel>
        get() = CreatePasswordViewModel::class.java

    override fun initView(
        binding: FragmentCreatePasswordBinding,
        viewModel: CreatePasswordViewModel
    ) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        binding.apply {
            initCheckbox()
        }
    }

    override fun proceedViewCommand(command: ViewCommand) {

    }

    private fun FragmentCreatePasswordBinding.initCheckbox() {
        var isChecked = false
        ivCheckedIcon.hide()
        rlCheckbox.setOnClickListener {
            if(isChecked){
                ivCheckedIcon.hide()
            } else {
                ivCheckedIcon.show()
            }
            isChecked = !isChecked
        }
        initCheckboxText()
    }

    private fun FragmentCreatePasswordBinding.initCheckboxText() {

        fun SpannableString.markText(text: String): SpannableString {
            this.setSpan(
                StyleSpan(Typeface.BOLD),
                this.indexOf(text),
                this.indexOf(text) + text.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            return this
        }

        fun SpannableString.setClickable(text: String, click: () -> Unit): SpannableString {
            this.setSpan(
                object : ClickableSpan() {
                    override fun onClick(p0: View) {
                        click()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.isUnderlineText = false
                    }
                },
                this.indexOf(text),
                this.indexOf(text) + text.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            return this
        }

        // TODO: 12/2/2020 add to resources
        val spannableString = SpannableString(tvTermsAndPolicy.text)
        spannableString.setClickable("Условия и Положения"){
            showToast("Условия и Положения")
        }
        spannableString.setClickable("Условия Конфидециальности"){
            showToast("Условия Конфидециальности")
        }
        spannableString.markText("Условия и Положения")
        spannableString.markText("Условия Конфидециальности")
        tvTermsAndPolicy.movementMethod = LinkMovementMethod.getInstance()
        tvTermsAndPolicy.text = spannableString
    }
}

