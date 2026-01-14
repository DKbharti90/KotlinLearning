package com.example.kotlinlearning.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorkManager(appContext: Context,params: WorkerParameters): Worker(appContext,params) {
    override fun doWork(): Result {
        println("Hello from WorkManager")

        return Result.success()
    }
}