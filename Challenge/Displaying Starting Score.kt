package com.averoes.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal lateinit var btnStart : Button
    internal lateinit var scoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView

    internal var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btn_start)
        scoreTextView = findViewById(R.id.score)
        timeLeftTextView = findViewById(R.id.timeleft)

        scoreTextView.text = getString(R.string.yourScore, score)

        btnStart.setOnClickListener { view ->
            incrementScore()
        }
    }

    private fun incrementScore() {

        score += 1
        val newScore = getString(R.string.yourScore, score)
        scoreTextView.text = newScore
    }
}
