package com.stefanusj.notesme.ui.note

import androidx.fragment.app.viewModels
import com.stefanusj.notesme.R
import com.stefanusj.notesme.databinding.NoteFragmentBinding
import com.stefanusj.notesme.ui.BaseFragment

class NoteFragment: BaseFragment<NoteFragmentBinding>() {

	override val layoutId: Int = R.layout.note_fragment
	override val viewModel: NoteViewModel by viewModels()

	override fun init() = with(binding) {
		viewModel = this@NoteFragment.viewModel

	}

}