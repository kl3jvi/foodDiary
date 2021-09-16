package com.kl3jvi.fooddiary.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kl3jvi.fooddiary.databinding.FragmentHomeBinding
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.network.RetrofitBuilder
import com.kl3jvi.fooddiary.utils.Status
import com.kl3jvi.fooddiary.viewmodel.MyDiaryViewModel
import com.kl3jvi.fooddiary.viewmodel.ViewModelFactory


class MyDiaryFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MyDiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(
                requireActivity(),
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
            ).get(
                MyDiaryViewModel::class.java
            )

        viewModel.getEntries().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.e("Ckemi", resource.data.toString())

                        Toast.makeText(
                            requireActivity(),
                            it.message ?: "Success",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.ERROR -> {

                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(
                            requireActivity(),
                            it.message ?: "Loading",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}