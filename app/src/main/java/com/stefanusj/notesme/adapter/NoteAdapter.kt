package com.stefanusj.notesme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stefanusj.notesme.databinding.NoteAddItemBinding
import com.stefanusj.notesme.databinding.NoteItemBinding
import com.stefanusj.notesme.repository.model.Note
import com.stefanusj.notesme.ui.note.NoteFragmentDirections

class NoteAdapter: ListAdapter<Note, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
		ADD  -> NoteAddViewHolder(
			NoteAddItemBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
		else -> NoteViewHolder(
			NoteItemBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
		when (position) {
			ADD_POSITION -> Unit
			else         -> {
				val note = getItem(position)
				(holder as NoteViewHolder).bind(note)
			}
		}

	override fun getItemViewType(position: Int): Int = when (position) {
		ADD_POSITION -> ADD
		else         -> NOTE
	}

	inner class NoteViewHolder(
		private val binding: NoteItemBinding
	): RecyclerView.ViewHolder(binding.root) {

		init {
			binding.setClickListener { view ->
				binding.note?.let {
					NoteFragmentDirections.toNoteEdit(it.id)
						.also(view.findNavController()::navigate)
				}
			}
		}

		fun bind(note: Note) {
			binding.apply {
				this.note = note
				executePendingBindings()
			}
		}
	}

	inner class NoteAddViewHolder(binding: NoteAddItemBinding):
		RecyclerView.ViewHolder(binding.root) {

		init {
			binding.setClickListener { view ->
				NoteFragmentDirections.toNoteAdd()
					.also(view.findNavController()::navigate)
			}
		}
	}

	companion object {

		private const val ADD = 1
		private const val NOTE = 2

		private const val ADD_POSITION = 0

		private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Note>() {
			override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
				return oldItem == newItem
			}
		}
	}
}