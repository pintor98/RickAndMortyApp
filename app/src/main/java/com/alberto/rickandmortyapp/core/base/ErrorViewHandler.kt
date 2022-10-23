package com.alberto.rickandmortyapp.core.base

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.alberto.rickandmortyapp.domain.model.common.AuthError
import com.alberto.rickandmortyapp.domain.model.common.RequestError
import com.alberto.rickandmortyapp.domain.model.common.ResponseError

interface ErrorViewHandler {

    fun showError(message: String?){}

    fun handleCommonError(baseViewModel: BaseViewModel, owner: LifecycleOwner, activity: Activity) {
        baseViewModel.error.observe(owner) { error ->
            when (error) {
                is AuthError.NotAuthorizedException -> showError("Ha ocurrido un error")
                is AuthError.ForbiddenException -> showError("Ha ocurrido un error")
                is RequestError.BadRequestException -> showError("Ha ocurrido un error")
                is ResponseError.MapperException -> showError("Ha ocurrido un error")
                is RequestError.NetworkException -> showError("Ha ocurrido un error")
                is RequestError.NotAllowedException -> showError("Ha ocurrido un error")
                is RequestError.NotFoundException -> showError("Ha ocurrido un error")
                is ResponseError.ServerException -> showError("Ha ocurrido un error")
                is ResponseError.UnknownException -> showError("Ha ocurrido un error")
                is ResponseError.NoConnectivityException -> showError("No estas conectado a la red")
            }
        }
    }
}