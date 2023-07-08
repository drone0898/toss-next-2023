package toss.next.drone0898.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import toss.next.drone0898.data.remote.ApiResult
import toss.next.drone0898.data.remote.ApiService
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiService: ApiService) {
    fun getList(serviceKey:String): Flow<ApiResult<Any>> =
        handleFlowApi {
            apiService.getSomeApi(serviceKey)
        }
}

fun <T : Any> handleFlowApi(execute: suspend () -> T,): Flow<ApiResult<T>> = flow {
    emit(ApiResult.Loading)
    try {
        emit(ApiResult.Success(execute()))
    } catch (e: HttpException) {
        emit(ApiResult.Fail.Error(code = e.code(), message = e.message()))
    } catch (e: Exception) {
        emit(ApiResult.Fail.Exception(e = e))
    }
}
