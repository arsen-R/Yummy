package com.example.recipeapp.core

import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

sealed class Result<out T> () {
    data object Loading: Result<Nothing>()
    data class Success<T>(val data: T?): Result<T>()
    data class Error(val exception: Throwable): Result<Nothing>()
}

fun <T> safeApiCall(
    call: suspend () -> T
): Flow<Result<T>> {
    return flow {
        emit(Result.Loading)
        try {
            val result = call()
            emit(Result.Success(result))
        } catch (e: SocketTimeoutException) {
            emit(Result.Error(e))
        } catch (e: UnresolvedAddressException) {
            emit(Result.Error(e))
        } catch (e: ResponseException) { // Ktor-specific
            emit(Result.Error(e))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}
