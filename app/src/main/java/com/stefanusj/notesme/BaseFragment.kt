package com.stefanusj.notesme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected abstract val layoutId: Int
    protected abstract fun init()

    protected lateinit var binding: DB

    protected open val viewModel: BaseViewModel by viewModels()
    protected open val arguments: NavArgs by navArgs()

    protected open fun subscribeUi(): Unit = with(viewModel) {
        setupMessage(requireContext(), messageText)
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