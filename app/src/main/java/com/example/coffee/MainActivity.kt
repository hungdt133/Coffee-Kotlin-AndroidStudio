package com.example.coffee

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val edtUsername = findViewById<TextInputEditText>(R.id.edtUsername)
        val edtPassword = findViewById<TextInputEditText>(R.id.edtPassword)
        val tvUsernameError = findViewById<TextView>(R.id.tvUsernameError)
        val tvPasswordError = findViewById<TextView>(R.id.tvPasswordError)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            var isValid = true

            if (username.isEmpty()) {
                tvUsernameError.visibility = TextView.VISIBLE
                isValid = false
            } else tvUsernameError.visibility = TextView.GONE

            if (password.isEmpty()) {
                tvPasswordError.visibility = TextView.VISIBLE
                isValid = false
            } else tvPasswordError.visibility = TextView.GONE

            if (isValid) {
                // TODO: Chuyển sang màn hình khác hoặc xử lý đăng nhập
            }
        }
    }
}
