package com.kl3jvi.fooddiary.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kl3jvi.fooddiary.R
import com.kl3jvi.fooddiary.databinding.FragmentHomeBinding
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.network.RetrofitBuilder
import com.kl3jvi.fooddiary.utils.Status
import com.kl3jvi.fooddiary.view.adapters.CustomEntryAdapter
import com.kl3jvi.fooddiary.viewmodel.MyDiaryViewModel
import com.kl3jvi.fooddiary.viewmodel.MyDiaryViewModelFactory


class MyDiaryFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CustomEntryAdapter
    private val viewModel: MyDiaryViewModel by viewModels {
        MyDiaryViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        binding.entryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CustomEntryAdapter(this@MyDiaryFragment)
        binding.entryRecyclerView.adapter = adapter
        observeEntries()

    }


    fun observeEntries() {
        Log.e("Une ", "u therrita")
        viewModel.getEntries().observe(viewLifecycleOwner, { res ->
            res?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { entry -> adapter.entriesList(entry) }
                        binding.entryRecyclerView.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireActivity(), res.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.entryRecyclerView.visibility = View.GONE
                    }
                }
            }
        })


    }


    fun deleteEntry(entryId: Int) {
        viewModel.deleteById(entryId)
        observeEntries()
    }


    override fun onResume() {
        super.onResume()
        observeEntries()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_all_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete_all -> {
                deleteAll()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete All Entries")
        builder.setMessage("Are you sure you want to delete all entries?")
        builder.setPositiveButton("Yes") { dialogInterface, _ ->
            viewModel.deleteAll()
            observeEntries()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("NO") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}