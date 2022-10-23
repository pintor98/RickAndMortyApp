package com.alberto.rickandmortyapp.domain.model.common

sealed class CustomException(open val code: Int?, message: String): Throwable(message), ResponseError,
    RequestError, AuthError

sealed interface RequestError {
    data class BadRequestException(
        override val message: String = "400 request error",
        override val code: Int = 400
    ): CustomException(code, message)

    data class NetworkException(
        override val message: String = "No tengo red",
        override val code: Int = 999
    ): CustomException(code, message)

    data class NotAllowedException(
        override val message: String = "405-Método HTTP no soportado",
        override val code: Int = 405
    ): CustomException(code, message)

    data class NotFoundException(
        override val message: String = "404,No se ha podido encontrar el recurso",
        override val code: Int = 404
    ): CustomException(code, message)
}

sealed interface ResponseError {
    data class UnknownException(val exception: Throwable, override val code: Int? = 999) :
        CustomException(code, exception.message ?: "UnknowException")

    data class NoConnectivityException(
        override val code: Int = 0,
        override val message: String = ""
    ): CustomException(code, message)

    data class ServerException(
        override val message: String = "500-503 server error",
        override val code: Int = 500
    ): CustomException(code, message)

    data class MapperException(
        override val message: String = "error en Mapeo de datos",
        override val code: Int = 600
    ): CustomException(code, message)
}

sealed interface AuthError {
    data class ForbiddenException(
        override val message: String = "forbidden",
        override val code: Int = 403
    ): CustomException(code, message)

    data class NotAuthorizedException(
        override val message: String = "necesitamos autentificación",
        override val code: Int = 401
    ): CustomException(code, message)
}