package com.alberto.rickandmortyapp.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivityBinding<VM: BaseViewModel>: AppCompatActivity(), ErrorViewHandler {
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOnCreated()
        initViewModel()
        listeners()
        observerViewModels()
        setBindingLayout()

        handleCommonError(viewModel, this, this)
    }

    open fun initOnCreated() {
        //implemented in class
    }

    open fun initViewModel() {
        //implemented in class
    }

    open fun observerViewModels() {
        //implemented in class
    }

    open fun listeners() {
        //implemented in class
    }

    open fun setBindingLayout() {
        //implemented in class
    }
}