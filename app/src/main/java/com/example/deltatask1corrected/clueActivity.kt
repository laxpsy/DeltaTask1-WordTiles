package com.example.deltatask1corrected

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton


class clueActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialogue)

        val clueText = intent.getStringExtra("clueText")
        val textView = findViewById<TextView>(R.id.ActualClueText)
        val okayClueButt = findViewById<MaterialButton>(R.id.clueStartButton)
        textView.text = clueText
        okayClueButt.setOnClickListener()
        {
            finish()
        }
    }
}