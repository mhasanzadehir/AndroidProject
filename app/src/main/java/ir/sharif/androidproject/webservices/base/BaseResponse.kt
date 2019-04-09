package ir.sharif.androidproject.webservices.base

import com.google.gson.annotations.Expose

open class BaseResponse {
    @Expose
    var messageBody: String? = null
}
