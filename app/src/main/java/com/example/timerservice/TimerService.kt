package com.example.timerservice

import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimerService : LifecycleService() {

    private var isRunning = false
    private var startTime = 0L
    private var elapsedTime = 0L
    private var timer: Job = lifecycleScope.launchWhenCreated { }
    private var timerState = LOADING

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.getStringExtra(MANAGE_ACTION_NAME)) {
            START -> {
                startTimer()
            }

            PAUSE -> {
                pauseTimer()
            }

            RESET -> {
                resetTimer()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


    private fun startTimer() {
        isRunning = true
        startTime = SystemClock.elapsedRealtime()

        timer = lifecycleScope.launch(Dispatchers.Default) {
            if(timerState == PAUSE){
                val lastTime = elapsedTime
                while (isRunning) {
                    val nowTime = SystemClock.elapsedRealtime() - startTime
                    elapsedTime = lastTime + nowTime
                    Log.d("nowTime", (elapsedTime / 1_000L).toString())
                }
            } else {
                while (isRunning) {
                    elapsedTime = SystemClock.elapsedRealtime() - startTime
                    Log.d("nowTime", (elapsedTime / 1_000L).toString())
                }
            }
        }
    }

    private fun pauseTimer() {
        isRunning = false
        timerState = PAUSE
        timer.cancel()
    }

    private fun resetTimer() {
        isRunning = false
        elapsedTime = 0L
        timerState = LOADING
        timer.cancel()
    }

    companion object {
        const val MANAGE_ACTION_NAME = "MANAGE_ACTION_NAME"
        const val LOADING = "LOADING"
        const val START = "START"
        const val PAUSE = "PAUSE"
        const val RESET = "RESET"
    }
}