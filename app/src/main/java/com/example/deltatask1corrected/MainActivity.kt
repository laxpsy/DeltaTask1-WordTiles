package com.example.deltatask1corrected

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.transition.Scene
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordEditText: EditText = findViewById(R.id.word)
        val clueEditText: EditText = findViewById(R.id.clue)
        val startButton: MaterialButton = findViewById(R.id.startButt)
        val highScore: TextView = findViewById(R.id.highScoreText)
        val sharedPref: SharedPreferences = this.getSharedPreferences("highScore", 0)
        val score: Int = sharedPref.getInt("score", 0)

        if(score != 0) {
            highScore.text = "Your highest score is ${score}"
        }

        startButton.setOnClickListener() {
           val wordText: String = wordEditText.text.toString()
           val clueText: String = clueEditText.text.toString()

           if(wordText.isNotEmpty() && clueText.isNotEmpty())
           {
           Intent(this, gameScreen::class.java).also {
               it.putExtra("gameWord", wordText)
               it.putExtra("gameClue", clueText)
               startActivity(it)
               overridePendingTransition(androidx.appcompat.R.anim.abc_slide_in_top, androidx.appcompat.R.anim.abc_slide_out_bottom)
                }
           }
           else {
               Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT).show()
           }
       }
    }
}