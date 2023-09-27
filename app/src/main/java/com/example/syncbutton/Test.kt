package com.example.syncbutton

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


suspend fun main() {
    val thread1 = Thread.currentThread()

    thread1.run {
        doWork()
        doWork2()
    }
}

suspend fun doWork() {
    delay(3000)
    print("doWork!!")
}

suspend fun doWork2() {
    delay(3000)
    print("doWork2!!")
}