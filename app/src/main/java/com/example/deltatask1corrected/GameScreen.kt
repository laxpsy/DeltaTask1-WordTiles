package com.example.deltatask1corrected


import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.CancellationSignal
import android.text.Layout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback.DismissEvent

class gameScreen: AppCompatActivity() {

    lateinit var guessList: MutableList<Char>
    lateinit var wordText: String
    lateinit var wordView: TextView
    lateinit var wordList: MutableList<Char>
    var butList: MutableList<MaterialButton> = mutableListOf()
    var count: Int = 0
    var lives: Int = 3
    var score: Int = 300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        //lateinit variables
        wordText = intent.getStringExtra("gameWord")!!.uppercase() //get wordText from prev activity
        val clueText = intent.getStringExtra("gameClue")!!.uppercase()  //get clueText from prev activity
        guessList = mutableListOf()
        wordView = findViewById<TextView>(R.id.wordView)
        wordList = wordText.toMutableList() //wordList to populate Tiles
        butList.add(0, findViewById<MaterialButton>(R.id.but1))
        butList.add(1, findViewById<MaterialButton>(R.id.but2))
        butList.add(2, findViewById<MaterialButton>(R.id.but3))
        butList.add(3, findViewById<MaterialButton>(R.id.but4))
        butList.add(4, findViewById<MaterialButton>(R.id.but5))
        butList.add(5, findViewById<MaterialButton>(R.id.but6))
        butList.add(6, findViewById<MaterialButton>(R.id.but7))
        butList.add(7, findViewById<MaterialButton>(R.id.but8))
        butList.add(8, findViewById<MaterialButton>(R.id.but9))
        butList.add(9, findViewById<MaterialButton>(R.id.but10))
        butList.add(10, findViewById<MaterialButton>(R.id.but11))
        butList.add(11, findViewById<MaterialButton>(R.id.but12))
        butList.add(12, findViewById<MaterialButton>(R.id.but13))
        butList.add(13, findViewById<MaterialButton>(R.id.but14))
        butList.add(14, findViewById<MaterialButton>(R.id.but15))
        butList.add(15, findViewById<MaterialButton>(R.id.but16))




        for (i in (1..wordText.length))
        {
            guessList.add('_')
        }

        //buttons and other objects declaration


        val resetButt: MaterialButton = findViewById<MaterialButton>(R.id.resetbutt)
        val checkButt: MaterialButton = findViewById<MaterialButton>(R.id.checkbutt)
        val clueButt: ImageView = findViewById<ImageView>(R.id.clueButton)

        shuffle()


        for(i in 0..15)
        {
            butList[i].setOnClickListener()
            {
                tileClicked(i)
            }
        }

        resetButt.setOnClickListener()
        {
            shuffle()
        }

        checkButt.setOnClickListener()
        {
            check()
        }

        clueButt.setOnClickListener()
        {
            Intent(this, clueActivity::class.java).apply {
                this.putExtra("clueText", clueText)
                startActivity(this)
                overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
            }
        }

    }

    fun tileClicked(i: Int)
        {
            if(count<wordText.length) {
                guessList[count] = wordList[i]
                count++
                wordView.text = guessList.joinToString("", "", "")
                butList[i].setBackgroundColor(getColor(R.color.transparent_grey))
            }

        }

        private fun shuffle()
        {

            if(wordList.size==16) //reset text
            {
                Toast.makeText(this, "Resetted", Toast.LENGTH_SHORT).show()
            }

            if (wordList.size!= 16) { //populate the tiles for the first time
                for (i in 1..(16 - wordText.length)) {
                    wordList.add(('A'..'Z').random())
                }
            }
            wordList.shuffle()

            //populate the tiles
            for(i in 0..15)
            {
                butList[i].text = wordList[i].toString()
            }

            for(i in (0..wordText.length-1))
            {
                guessList[i]='_'
            }

            count=0

            wordView.text = guessList.joinToString("", "", "")

            for(i in 0..15)
            {
                butList[i].setBackgroundColor(getColor(R.color.transparent_white))
            }



        }

    fun check()
    {
        if(guessList.joinToString("","","") == wordText) {
            Toast.makeText(this, "Correct guess!", Toast.LENGTH_SHORT).show()
            Intent(this, gameOver::class.java).apply {
                this.putExtra("score", score)
                startActivity(this)
                overridePendingTransition(
                    com.google.android.material.R.anim.m3_motion_fade_enter,
                    com.google.android.material.R.anim.m3_motion_fade_exit
                )
            }
        }
        else if (lives > 0) {
            Toast.makeText(this, "Lost a life", Toast.LENGTH_SHORT).show()
            if(lives == 3 )
            {
                findViewById<ImageView>(R.id.life1).setColorFilter(R.color.transparent_grey)
                lives--
                shuffle()
                score-=50
            }
            else if(lives == 2)
            {
                findViewById<ImageView>(R.id.life2).setColorFilter(R.color.transparent_grey)
                lives--
                shuffle()
                score-=100
            }
            else
            {lives--
                findViewById<ImageView>(R.id.life3).setColorFilter(R.color.transparent_grey)
                Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show()
                finish()}
        }
    }


}
