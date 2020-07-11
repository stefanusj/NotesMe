package com.stefanusj.notesme.ui.profile

import androidx.fragment.app.viewModels
import com.stefanusj.notesme.R
import com.stefanusj.notesme.databinding.ProfileFragmentBinding
import com.stefanusj.notesme.ui.BaseFragment

class ProfileFragment: BaseFragment<ProfileFragmentBinding>() {

	override val layoutId: Int = R.layout.profile_fragment
	override val viewModel: ProfileViewModel by viewModels()

	override fun init() = with(binding) {
		viewModel = this@ProfileFragment.viewModel
	}

}