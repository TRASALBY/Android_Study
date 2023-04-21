package com.example.timerservice

fun convertTimeStamp(nowTime:Long): String {
    val timeToSec = (nowTime / 1000).toInt()
    val hours: Int = (timeToSec / 60) / 60
    val minutes: Int = (timeToSec / 60) % 60
    val seconds: Int = timeToSec % 60
    val milliSec = (nowTime % 1000) / 10

    return "${"%02d".format(hours)}:${"%02d".format(minutes)}:${"%02d".format(seconds)}.${"%02d".format(milliSec)}"
}