package com.example.deltatask1corrected

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import kotlin.system.exitProcess

class gameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gameoverdialogue)

       val score = intent.getIntExtra("score", 0)
        val scoreText: TextView = findViewById(R.id.scoreText)
        val playAgain: MaterialButton = findViewById(R.id.playAgainButt)
        val exit: MaterialButton = findViewById(R.id.exitButt)
        val sharedPref: SharedPreferences = this.getSharedPreferences("highScore", 0)

        setFinishOnTouchOutside(false)

        scoreText.text = "Your score is ${score}"

        val editor = sharedPref.edit()
        if(score > sharedPref.getInt("score", 0)) {
            editor.putInt("score", score)
            editor.apply()
        }
        playAgain.setOnClickListener()
        {
            Intent(this, MainActivity::class.java).also()
            {
                startActivity(it)
                overridePendingTransition(com.google.android.material.R.anim.m3_motion_fade_enter, com.google.android.material.R.anim.m3_motion_fade_exit)
            }
        }

        exit.setOnClickListener()
        {
            finishAndRemoveTask()
        }




    }
}