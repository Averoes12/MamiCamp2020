package com.averoes.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

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

        if (savedInstanceState != null){
           score =  savedInstanceState.getInt(SCORE_KEY)
           timeLeftOnTimer =  savedInstanceState.getLong(TIMELEFT_KEY)
            restoreGame()
        }else{
            resetGame()
        }

        btnStart.setOnClickListener { view ->
            val bounceAnimator = AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimator)
            incrementScore()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.info, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about){
            showInfo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.aboutTitle, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.aboutMessage)

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(dialogTitle)
        dialogBuilder.setMessage(dialogMessage)
        dialogBuilder.create().show()
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
        val blinkAnimator = AnimationUtils.loadAnimation(this, R.anim.blink)
        scoreTextView.startAnimation(blinkAnimator)
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

    private fun restoreGame(){
        scoreTextView.text = getString(R.string.yourScore, score)

        val restoredTime = timeLeftOnTimer / 1000
        timeLeftTextView.text = getString(R.string.timeLeft, restoredTime)

        countDownTimer = object : CountDownTimer(timeLeftOnTimer, countDownInterval){
            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished
                val timeLeft = millisUntilFinished /1000
                timeLeftTextView.text = getString(R.string.timeLeft, timeLeft)
            }

        }
        countDownTimer.start()
        gameStarted = true
    }

}
