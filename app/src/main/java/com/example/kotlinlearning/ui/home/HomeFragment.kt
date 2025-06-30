package com.example.kotlinlearning.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlinlearning.data.entity.Module
import com.example.kotlinlearning.databinding.FragmentHomeFragementBinding
import com.example.kotlinlearning.ui.adapter.module.ModuleAdapter
import com.example.kotlinlearning.ui.viewModel.HomeViewModel
import com.example.kotlinlearning.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

/**
 * A simple main Home [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeFragementBinding
    private val homeViewModel : HomeViewModel by viewModels()
    var channel = Channel<Int>();
    private val channels=Channel<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        producer()
        consumer()
    }

    /**
     * channel
     * */
    fun producer(){
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }
    }
    fun consumer(){
        CoroutineScope(Dispatchers.Main).launch {
            channel.receive()
            channel.receive()
        }
    }

    /**
     *
     * flow
     * */

    fun produce()= flow<Int> {
        val list =listOf<Int>(1,2,3,4,5,6,7,8,9,10)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }


    fun collectFlow(){
       val job= CoroutineScope(Dispatchers.Main).launch {
            produce().collect{
                println("Collected $it")
            }
        }


    }

    fun collectFlowWithCancel(){
        val job= CoroutineScope(Dispatchers.Main).launch {
            produce().collect{
                println("Collected $it")
            }
        }


        CoroutineScope(Dispatchers.Main).launch {
            delay(2300)
            job.cancel()
        }
    }

    fun collectFlowWithMultipleCollect(){
        val job= CoroutineScope(Dispatchers.Main).launch {
            produce().collect{
                println("Collected 1 $it")
            }
        }


        CoroutineScope(Dispatchers.Main).launch {
            produce().collect{
                println("Collected 2 $it")
            }
        }
    }

    fun collectFlowWithMultipleCollectAndDelay(){
        val job= CoroutineScope(Dispatchers.Main).launch {
            produce().collect{
                println("Collected 1 $it")
            }
        }


        CoroutineScope(Dispatchers.Main).launch {
            delay(2300)
            produce().collect{
                println("Collected 2 $it")
            }
        }
    }


    fun collectFlowOperator() {
        CoroutineScope(Dispatchers.Main).launch {
            produce()
                .onStart {
                    println(" Stated")
                }
                .onCompletion {
                    println(" Completed ")
                }
                .onEach {
                    println(" Emitting $it")
                }
                .collect {
                    println("Collected $it")
                }
        }

    }

    fun collectFlowOperatorUse() {
        CoroutineScope(Dispatchers.Main).launch {
            produce()
                .onStart {
                    emit(-1)
                    println(" Stated")
                }
                .onCompletion {
                    emit(6)
                    println(" Completed ")
                }
                .onEach {
                    println(" Emitting $it")
                }
                .collect {
                    println("Collected $it")
                }
        }

    }

    fun collectFlowOperator2() {
        CoroutineScope(Dispatchers.Main).launch {
            val firstElementOfFlow=produce().first()
            println("Collected $firstElementOfFlow")

        }

        CoroutineScope(Dispatchers.Main).launch {
            val allElementOfFlow=produce().toList()
            println("Collected $allElementOfFlow")

        }

        CoroutineScope(Dispatchers.Main).launch {
            val allElementOfFlow=produce()
            allElementOfFlow
                .map { it*2 }
                .filter { it<8 }
                .collect {
                println("Collected $it")
            }

        }

        CoroutineScope(Dispatchers.Main).launch {


            val time= measureTimeMillis {

                produce()
                    .buffer(3)
                    .collect{
                    delay(1400)
                    println("Collected $it")
                }

            }
            println("timeTaken ${time}")

        }

        producerForTheChannel()
        consumerForChannel()
    }


    fun producerForTheChannel(){
        lifecycleScope.launch {
            channels.send(1)
            channels.send(2)
            channels.send(3)
        }
    }

    fun consumerForChannel(){
        lifecycleScope.launch {
            Log.d("CHANNEL_DATA",channels.receive().toString())
            Log.d("CHANNEL_DATA",channels.receive().toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        homeBinding=FragmentHomeFragementBinding.inflate(layoutInflater)
        val adapter=ModuleAdapter( itemClickListener = { view, module -> openActivity(view, module)} )
        homeBinding.modelList.adapter=adapter
        lifecycleScope.launch(Dispatchers.IO) {
            homeViewModel.insertModule()
        }

        lifecycleScope.launch {
            homeViewModel.allModule.collectLatest { adapter.submitData(it) }
        }
        return homeBinding.root
    }

    private fun openActivity(view: View, module: Module) {
    }





}