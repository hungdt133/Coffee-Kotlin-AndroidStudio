package com.example.coffeeshop

import android.content.Intent // <-- THÊM IMPORT NÀY
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response // <-- THÊM IMPORT NÀY
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Bỏ btnLoginTab và btnSignUpTab vì không có trong layout của bạn
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvForgot = findViewById<TextView>(R.id.tvForgot)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val client = OkHttpClient()
                val json = JSONObject()
                json.put("username", username)
                json.put("password", password)

                val requestBody = RequestBody.create(
                    "application/json; charset=utf-8".toMediaType(),
                    json.toString()
                )

                val request = Request.Builder()
                    .url("https://c76lgf-3000.csb.app/login")
                    .post(requestBody)
                    .build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        runOnUiThread {
                            // SỬA Ở ĐÂY
                            Toast.makeText(this@LoginActivity, "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val responseBody = response.body?.string()
                        runOnUiThread {
                            if (response.isSuccessful && responseBody != null) {
                                val jsonRes = JSONObject(responseBody)
                                val message = jsonRes.optString("message", "")
                                val user = jsonRes.optJSONObject("user")

                                // SỬA Ở ĐÂY
                                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()

                                if (user != null) {
                                    // Chuyển màn hình chính (MainActivity)
                                    // SỬA Ở ĐÂY
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    intent.putExtra("username", user.optString("username"))
                                    startActivity(intent)
                                    finish() // Kết thúc LoginActivity để người dùng không quay lại được bằng nút back
                                }
                            } else {
                                // SỬA Ở ĐÂY
                                Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }

        tvForgot.setOnClickListener {
            // Chuyển sang màn hình "Forgot Password"
        }

        // Không có btnSignUpTab trong layout của bạn, có thể bạn muốn xử lý cho một TextView khác
    }
}
