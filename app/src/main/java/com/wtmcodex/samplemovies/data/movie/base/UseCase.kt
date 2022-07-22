package com.wtmcodex.samplemovies.data.movie.base

import android.util.Log
import com.wtmcodex.samplemovies.model.RemoteException
import com.wtmcodex.samplemovies.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.Duration

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: RemoteException) {
            Log.e("ERROR", "Exception executing UseCase $this with parameters $parameters", e)
            Result.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R

    suspend fun retry(
        parameters: P,
        limit: Int? = null,
        delay: Duration = Duration.ZERO
    ): Result<R> {
        require(limit == null || limit > 0) { "Limit can't be negative" }
        val retries = 0
        var result: Result<R>
        do {
            result = invoke(parameters)
            if (result is Result.Success) return result
            delay(delay.toMillis())
        } while (limit == null || retries < limit)
        return result
    }
}
