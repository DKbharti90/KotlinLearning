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
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple main Home [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeFragementBinding
    private val homeViewModel : HomeViewModel by viewModels()
    private val channels=Channel<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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