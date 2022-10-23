package com.alberto.rickandmortyapp.core.base

import com.alberto.rickandmortyapp.domain.model.common.*
import retrofit2.HttpException

fun toDomainException(exception: Throwable): CustomException {
    return try {
        if (exception is NoConnectionIOException || exception is ResponseError.NoConnectivityException) {
            ResponseError.NoConnectivityException()
        } else {
            when ((exception as HttpException).code()) {
                400 -> RequestError.BadRequestException()
                401 -> AuthError.NotAuthorizedException()
                403 -> AuthError.ForbiddenException()
                404 -> RequestError.NotFoundException()
                405 -> RequestError.NotAllowedException()
                500 -> ResponseError.ServerException()
                else -> ResponseError.ServerException()
            }
        }
    } catch (exception: Exception) {
        ResponseError.UnknownException(exception)
    }
}