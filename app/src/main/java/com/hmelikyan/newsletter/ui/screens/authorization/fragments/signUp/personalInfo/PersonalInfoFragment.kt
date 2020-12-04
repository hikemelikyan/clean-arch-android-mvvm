package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.personalInfo

import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegister
import com.hmelikyan.newsletter.databinding.FragmentPersonalInfoBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseFragment
import com.hmelikyan.newsletter.root.ext.toServerDate
import java.util.*

class PersonalInfoFragment : BaseFragment<FragmentPersonalInfoBinding>() {

	private val args : PersonalInfoFragmentArgs by navArgs()

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalInfoBinding
		get() = FragmentPersonalInfoBinding::inflate

	override fun initView(binding : FragmentPersonalInfoBinding) {
		sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
		binding.apply {
			etDOB.setOnClickListener {
				showToast("Date picker")
			}
			tvNext.setOnClickListener {
				val extras = FragmentNavigatorExtras(
					appIcon to appIcon.transitionName,
					tvNext to tvNext.transitionName,
					title to title.transitionName
				)
				findNavController().navigate(PersonalInfoFragmentDirections.actionPersonalInfoFragmentToCreatePasswordFragment(getValidRequestModel(Date())), extras)
			}
		}
	}

	private fun getValidRequestModel(dob : Date) : ReqModelRegister {
		return with(mBinding) {
			ReqModelRegister(
				args.codeId,
				etName.text.toString(),
				etSurname.text.toString(),
				etSecondName.text.toString(),
				dob.toServerDate(),
				1
			)
		}
	}

}
