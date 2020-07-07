package com.stefanusj.notesme.ui.note.add

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.stefanusj.notesme.R
import com.stefanusj.notesme.databinding.NoteAddFragmentBinding
import com.stefanusj.notesme.ui.BaseFragment
import dev.sasikanth.colorsheet.ColorSheet

class NoteAddFragment: BaseFragment<NoteAddFragmentBinding>() {

	override val layoutId: Int = R.layout.note_add_fragment
	override val viewModel: NoteAddViewModel by viewModels()

	private val colorSheet: ColorSheet by lazy { ColorSheet() }
	private val colors: IntArray by lazy { resources.getIntArray(R.array.note_colors) }
	private var selectedColor = 0

	override fun init() = with(binding) {
		viewModel = this@NoteAddFragment.viewModel

		mbColor.setOnClickListener(onColorClicked)
	}

	override fun subscribeUi(): Unit = with(viewModel) {
		super.subscribeUi()
		onColorSet(colors.first())
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

