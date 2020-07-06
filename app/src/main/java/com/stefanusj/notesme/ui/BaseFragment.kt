package com.stefanusj.notesme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stefanusj.notesme.R
import com.stefanusj.notesme.helper.setupMessage

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

	protected abstract val layoutId: Int
	protected abstract fun init()

	protected lateinit var binding: DB

	protected open val viewModel: BaseViewModel by viewModels()
	protected open val arguments: NavArgs by navArgs()

	protected open val shouldAuthentication = true

	protected open fun subscribeUi(): Unit = with(viewModel) {
		setupMessage(requireContext(), messageText)
		if (shouldAuthentication) {
			authenticated.observe(viewLifecycleOwner) { authenticated ->
				if (authenticated) return@observe
				findNavController().navigate(R.id.loginFragment)
			}
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
		binding.lifecycleOwner = this
		lifecycle.addObserver(viewModel)
		init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }
}