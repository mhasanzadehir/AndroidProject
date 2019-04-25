package ir.sharif.androidproject.webservice.base

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ir.sharif.androidproject.webservice.base.WebserviceAddresses.BASE_URL

object MyRetrofit {
    var webserviceUrls: WebserviceUrls = getUrls()

    private fun getUrls(): WebserviceUrls {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val builder = OkHttpClient.Builder()

        addLogInterceptor(builder)

        val client = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit.create(WebserviceUrls::class.java)
    }

    private fun addLogInterceptor(client: OkHttpClient.Builder) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
    }
}
