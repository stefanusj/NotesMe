package com.stefanusj.notesme.ui.note.edit

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.stefanusj.notesme.R
import com.stefanusj.notesme.databinding.NoteEditFragmentBinding
import com.stefanusj.notesme.ui.BaseFragment
import dev.sasikanth.colorsheet.ColorSheet

class NoteEditFragment: BaseFragment<NoteEditFragmentBinding>() {

	override val layoutId: Int = R.layout.note_edit_fragment
	override val viewModel: NoteEditViewModel by viewModels()
	override val arguments: NoteEditFragmentArgs by navArgs()

	private val colorSheet: ColorSheet by lazy { ColorSheet() }
	private val colors: IntArray by lazy { resources.getIntArray(R.array.note_colors) }
	private var selectedColor = 0

	override fun init() = with(binding) {
		viewModel = this@NoteEditFragment.viewModel
		mbColor.setOnClickListener(onColorClicked)
	}

	override fun subscribeUi(): Unit = with(viewModel) {
		super.subscribeUi()
		setNote(arguments.id)

		color.observe(viewLifecycleOwner) { selectedColor = it }
	}

	private val onColorClicked = View.OnClickListener {
		colorSheet.colorPicker(
			colors = colors,
			selectedColor = selectedColor,
			listener = { viewModel.onColorSet(it) }
		).show(childFragmentManager)
	}

}