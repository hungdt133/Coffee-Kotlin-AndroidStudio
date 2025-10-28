package com.example.coffeeshop.ui.activity

import android.content.Intent // <-- THÊM IMPORT NÀY
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshop.R
import com.example.coffeeshop.data.dao.UserDAO
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
                return@setOnClickListener
            }

            // Gọi hàm checkLogin trong UserDAO
            UserDAO.checkLogin(username, password) { success, message, user ->
                runOnUiThread {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                    if (success && user != null) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("username", user.optString("username"))
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        tvForgot.setOnClickListener {
            // TODO: Chuyển sang màn hình quên mật khẩu
        }

        // Không có btnSignUpTab trong layout của bạn, có thể bạn muốn xử lý cho một TextView khác
    }
}
