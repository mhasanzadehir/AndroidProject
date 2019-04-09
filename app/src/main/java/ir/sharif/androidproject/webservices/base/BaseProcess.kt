package ir.sharif.androidproject.webservices.base

import java.io.IOException

import retrofit2.Call

abstract class BaseProcess {

    @Throws(IOException::class, WebserviceException::class)
    protected fun <T> send(call: Call<T>): T {
        val execute = call.execute()
        if (execute.code() > 299 || execute.code() < 200) {
            throw WebserviceException(execute.code(), execute.errorBody().string())
        }
        return execute.body()
    }

    @Throws(IOException::class, WebserviceException::class)
    abstract fun  process(): Any

}
