package com.fadybassem.core

import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HandleApiError @Inject constructor(private val resourceProvider: ResourceProvider) {

    fun handleApiErrors(error: Throwable): String {
        return when (error) {
            is HttpException -> {
                val code = error.code()
                val errorMessage = getErrorMessageFromCode(code)
                errorMessage
            }

            is IOException -> {
                resourceProvider.getString(R.string.no_internet_connection)
            }

            is TimeoutException -> {
                resourceProvider.getString(R.string.request_timed_out)
            }

            else -> {
                resourceProvider.getString(R.string.something_went_wrong)
            }
        }
    }

    private fun getErrorMessageFromCode(code: Int): String {
        return when (code) {
            400 -> resourceProvider.getString(R.string.bad_request)
            401 -> resourceProvider.getString(R.string.unauthorized)
            403 -> resourceProvider.getString(R.string.forbidden)
            404 -> resourceProvider.getString(R.string.not_found)
            500 -> resourceProvider.getString(R.string.server_error)
            else -> resourceProvider.getString(R.string.unknown_error)
        }
    }
}