package com.example.kotlinlearning.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.data.entity.Module
import com.example.kotlinlearning.databinding.FragmentHomeFragementBinding
import com.example.kotlinlearning.databinding.FragmentMainBinding
import com.example.kotlinlearning.ui.adapter.module.ModuleAdapter
import com.example.kotlinlearning.ui.adapter.module.ModuleViewHolder
import com.example.kotlinlearning.ui.viewModel.HomeViewModel
import com.example.kotlinlearning.ui.viewModel.MainViewModel
import com.example.kotlinlearning.ui.viewModel.factory.HomeViewModelFactory
import com.example.kotlinlearning.ui.viewModel.factory.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple main Home [Fragment] subclass.
 * Use the [HomeFragement.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragement : Fragment() {
    private lateinit var homeBinding: FragmentHomeFragementBinding
    private val homeViewModel by viewModels<HomeViewModel> { activity?.let { HomeViewModelFactory(it.application) }!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        homeBinding=FragmentHomeFragementBinding.inflate(layoutInflater)
        val adapter=ModuleAdapter( itemClickListener = { view, module -> openActivity(view, module)} )
        homeBinding.modelList.adapter=adapter
        lifecycleScope.launch {
            homeViewModel.allModule.collectLatest { adapter.submitData(it) }
        }
        return homeBinding.root
    }

    private fun openActivity(view: View, module: Module) {

        Toast.makeText(context,"CLICLKED",Toast.LENGTH_LONG).show()
       // findNavController().navigate(module.navigation)

    }


}