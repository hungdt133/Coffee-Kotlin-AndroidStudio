package com.example.coffeeshop.data.dao

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import java.io.IOException

object UserDAO {
    private val client = OkHttpClient()
    private const val BASE_URL = "https://c76lgf-3000.csb.app"

    fun checkLogin(username: String, password: String, callback: (success: Boolean, message: String, user: JSONObject?) -> Unit) {
        val json = JSONObject().apply {
            put("username", username)
            put("password", password)
        }

        val requestBody = RequestBody.create(
            "application/json; charset=utf-8".toMediaType(),
            json.toString()
        )

        val request = Request.Builder()
            .url("$BASE_URL/login")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(false, "Network error: ${e.message}", null)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    val jsonRes = JSONObject(responseBody)
                    val message = jsonRes.optString("message", "")
                    val user = jsonRes.optJSONObject("user")
                    callback(true, message, user)
                } else {
                    callback(false, "Login failed!", null)
                }
            }
        })
    }
}