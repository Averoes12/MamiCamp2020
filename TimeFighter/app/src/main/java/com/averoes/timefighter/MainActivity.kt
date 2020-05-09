package com.averoes.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var btnStart : Button
    internal lateinit var scoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView

    internal var score = 0

    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown:Long = 60000
    internal var countDownInterval:Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btn_start)
        scoreTextView = findViewById(R.id.score)
        timeLeftTextView = findViewById(R.id.timeleft)

        resetGame()

        btnStart.setOnClickListener { view ->
            incrementScore()
        }
    }

    private fun resetGame(){
        score = 0

        scoreTextView.text = getString(R.string.yourScore, score)

        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.timeLeft, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval){
            override fun onFinish() {
                endGame()
                btnStart.text = getString(R.string.startGame)
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.timeLeft, timeLeft)
            }

        }
        gameStarted = false
        btnStart.text = getString(R.string.startGame)
    }

    private fun incrementScore() {
        if (!gameStarted){
            startGame()
        }
        score += 1
        val newScore = getString(R.string.yourScore, score)
        scoreTextView.text = newScore
    }

    private fun startGame(){
        countDownTimer.start()
        gameStarted = true
        btnStart.text = getString(R.string.tapMe)
    }

    private fun endGame(){
        Toast.makeText(this, getString(R.string.gameOver, score), Toast.LENGTH_LONG).show()
        resetGame()
    }

}
