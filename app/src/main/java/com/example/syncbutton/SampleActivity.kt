package com.example.syncbutton

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val asyncButton = findViewById<Button>(R.id.asyncButton)
        val syncButton = findViewById<Button>(R.id.syncButton)

        asyncButton.setOnClickListener {
            val startTime = System.currentTimeMillis()
            val job1 = thread {
                val result = doWork()
                Log.e("AsyncTask", "Task 1: $result")
            }

            job1.join()

            val jobs = mutableListOf<Thread>()

            for (i in 2..5) {
                val job = thread {
                    val result = doWork()
                    Log.e("AsyncTask", "Task $i: $result")
                }
                jobs.add(job)
            }

            jobs.forEach { it.join() }

            val result6 = doWork()
            Log.e("AsyncTask", "Task 6: $result6")

            val endTime = System.currentTimeMillis()
            val resultTime = (endTime - startTime) / 1000
            Log.e("resultTime", "resultTime = $resultTime 초")
        }

        syncButton.setOnClickListener {
            val startTime = System.currentTimeMillis()

            for (i in 1..6) {
                val result = doWork()
                Log.e("SyncTask", "Task $i: $result")
            }

            val endTime = System.currentTimeMillis()
            val resultTime = (endTime - startTime) / 1000
            Log.e("resultTime", "resultTime = $resultTime 초")
        }
    }

    private fun doWork(): String {
        Thread.sleep(1000)
        return "작업 완료"
    }
}