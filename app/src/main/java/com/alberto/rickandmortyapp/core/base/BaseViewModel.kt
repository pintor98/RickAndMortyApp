package com.alberto.rickandmortyapp.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alberto.rickandmortyapp.domain.model.common.CustomException

abstract class BaseViewModel: ViewModel() {

    protected val _error = MutableLiveData<CustomException>()
    val error: LiveData<CustomException> get() = _error
}