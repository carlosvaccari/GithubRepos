package com.cvaccari.features.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.kodein.di.KodeinAware

abstract class BindableBaseFragment<T : ViewBinding> : Fragment(), KodeinAware {

    private var _binding: T? = null

    protected val binding get() = _binding!!

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun initializeViewBinding(view: View): T

    abstract fun initComponents(binding: T)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initializeViewBinding(inflater.inflate(getLayoutResource(), container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(binding)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
