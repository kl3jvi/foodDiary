package com.kl3jvi.fooddiary.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kl3jvi.fooddiary.databinding.FragmentHomeBinding
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.network.RetrofitBuilder
import com.kl3jvi.fooddiary.utils.Status
import com.kl3jvi.fooddiary.view.adapters.CustomEntryAdapter
import com.kl3jvi.fooddiary.viewmodel.MyDiaryViewModel
import com.kl3jvi.fooddiary.viewmodel.ViewModelFactory


class MyDiaryFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CustomEntryAdapter
    private lateinit var viewModel: MyDiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel =
            ViewModelProvider(
                requireActivity(),
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
            ).get(
                MyDiaryViewModel::class.java
            )
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.entryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CustomEntryAdapter(this@MyDiaryFragment)
        binding.entryRecyclerView.adapter = adapter
        observeEntries()
    }


    private fun observeEntries() {
        viewModel.getEntries().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { entry -> adapter.entriesList(entry) }
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


    fun deleteEntry(entryId: Int) {
        viewModel.deleteById(entryId)
        observeEntries()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}