package com.averoes.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
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
    internal var timeLeftOnTimer:Long = 60000

    companion object{
        private val TAG = MainActivity::class.java.simpleName
        private const val SCORE_KEY = "SCORE_KEY"
        private const val TIMELEFT_KEY = "TIMELEFT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called. Score is: $score")

        btnStart = findViewById(R.id.btn_start)
        scoreTextView = findViewById(R.id.score)
        timeLeftTextView = findViewById(R.id.timeleft)

        resetGame()

        btnStart.setOnClickListener { view ->
            incrementScore()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SCORE_KEY, score)
        outState.putLong(TIMELEFT_KEY, timeLeftOnTimer)
        countDownTimer.cancel()

        Log.d(TAG, "onSaveInstanceState called. Saving: Your score: $score & Time left: $timeLeftOnTimer")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
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
                timeLeftOnTimer = millisUntilFinished
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
