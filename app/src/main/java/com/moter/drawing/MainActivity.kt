package com.moter.drawing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cDrawing = findViewById<CustomDrawing>(R.id.cDrawing)
        val btnClear = findViewById<AppCompatButton>(R.id.btnClear)

        btnClear.setOnClickListener {
            cDrawing.clear()
        }
    }
}