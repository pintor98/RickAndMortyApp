package com.alberto.rickandmortyapp.core.base

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.alberto.rickandmortyapp.domain.model.common.AuthError
import com.alberto.rickandmortyapp.domain.model.common.RequestError
import com.alberto.rickandmortyapp.domain.model.common.ResponseError

interface ErrorViewHandler {

    fun handleCommonError(baseViewModel: BaseViewModel, owner: LifecycleOwner, activity: Activity) {
        baseViewModel.error.observe(owner) { error ->
            when (error) {
                is AuthError.NotAuthorizedException -> ""
                is AuthError.ForbiddenException -> ""
                is RequestError.BadRequestException -> ""
                is ResponseError.MapperException -> ""
                is RequestError.NetworkException -> ""
                is RequestError.NotAllowedException -> ""
                is RequestError.NotFoundException -> ""
                is ResponseError.ServerException -> ""
                is ResponseError.UnknownException -> ""
                is ResponseError.NoConnectivityException -> ""
            }
        }
    }
}