package com.example.coffeeshop.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshop.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Tạo một layout đơn giản cho MainActivity
        // Bạn có thể tạo file layout riêng (activity_main.xml) và dùng setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        // Nhận username được gửi từ LoginActivity
        val username = intent.getStringExtra("username")

        // Hiển thị lời chào
        tvWelcome.text = "Welcome, $username!"
    }
}
