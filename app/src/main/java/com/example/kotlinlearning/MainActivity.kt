package com.example.kotlinlearning

import android.arch.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.kotlinlearning.databinding.ActivityMainBinding
import com.example.kotlinlearning.workmanager.MyWorkManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


/**
 *
 * Main Activity / First Activity
 *
 * */
@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var  navController :NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
       // var navController = findNavController(R.id.nav_host_fragment)
        val navigationHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navigationHost.navController


        val myWorkRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<MyWorkManager>()
                .setConstraints(
                    Constraints.Builder().
                    setRequiredNetworkType(NetworkType.CONNECTED).build()
                ).setBackoffCriteria(BackoffPolicy.LINEAR,
                    OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS)
                .build()
        WorkManager.getInstance().enqueue(myWorkRequest)

        /**
         * for Observe the the Work manager result

        WorkManager.getInstance().getWorkInfoByIdLiveData(myWorkRequest.id)
            .observe(lifecycleOwner, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    // Work finished successfully
                }
            })*/

        setContentView(mainBinding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}