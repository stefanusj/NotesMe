package com.stefanusj.notesme.ui.note

import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.stefanusj.notesme.R
import com.stefanusj.notesme.adapter.NoteAdapter
import com.stefanusj.notesme.databinding.NoteFragmentBinding
import com.stefanusj.notesme.ui.BaseFragment

class NoteFragment: BaseFragment<NoteFragmentBinding>() {

	override val layoutId: Int = R.layout.note_fragment
	override val viewModel: NoteViewModel by viewModels()

	private val adapter: NoteAdapter by lazy { NoteAdapter() }

	override fun init() = with(binding) {
		viewModel = this@NoteFragment.viewModel
		rvNote.adapter = adapter
	}

	override fun subscribeUi(): Unit = with(viewModel) {
		super.subscribeUi()
		note.observe(viewLifecycleOwner) {
			adapter.submitList(it)
		}
	}

}