package com.example.kotlinlearning.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.kotlinlearning.data.entity.Module
import com.example.kotlinlearning.databinding.FragmentHomeFragementBinding
import com.example.kotlinlearning.ui.adapter.module.ModuleAdapter
import kotlinx.coroutines.launch

/**
 * A simple main Home [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeFragementBinding

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
           // homeViewModel.allModule.collectLatest { adapter.submitData(it) }
        }
        return homeBinding.root
    }

    private fun openActivity(view: View, module: Module) {

        Toast.makeText(context,"CLICLKED",Toast.LENGTH_LONG).show()
       // findNavController().navigate(module.navigation)

    }


}