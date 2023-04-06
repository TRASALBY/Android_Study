package com.example.timerservice

import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimerService: LifecycleService() {

    private var isRunning = false
    private var startTime = 0L
    private var elapsedTime = 0L
    private lateinit var timer : Job

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

    override fun onCreate() {
        super.onCreate()
        timer = lifecycleScope.launch {
            while (true) {
                if(isRunning) {
                    elapsedTime = SystemClock.elapsedRealtime() - startTime
                    if (elapsedTime % 1000L == 0L){
                        Log.d("nowTime",(elapsedTime % 1000L).toString())
                    }
                }
            }
        }
    }


    private fun startTimer(){
        startTime = SystemClock.elapsedRealtime()
        isRunning = true
    }

    private fun pauseTimer() {
        if(isRunning) {
            elapsedTime += SystemClock.elapsedRealtime() - startTime
            isRunning = false
        }
    }

    private fun resetTimer(){
        if(isRunning.not()){
            elapsedTime = 0L
        }
    }

    companion object {
        const val MANAGE_ACTION_NAME = "MANAGE_ACTION_NAME"
        const val LOADING = "LOADING"
        const val START = "START"
        const val PAUSE = "PAUSE"
        const val RESET = "RESET"
    }
}