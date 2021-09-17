package com.kl3jvi.fooddiary.view.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.kl3jvi.fooddiary.databinding.ActivityAddEntryBinding
import com.kl3jvi.fooddiary.model.entities.entries.CreateEntry
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.network.RetrofitBuilder
import com.kl3jvi.fooddiary.utils.Status
import com.kl3jvi.fooddiary.view.adapters.CustomAddAdapter
import com.kl3jvi.fooddiary.viewmodel.AddEntryActivityViewModel
import com.kl3jvi.fooddiary.viewmodel.AddEntryViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class AddEntryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddEntryBinding
    private lateinit var viewModel: AddEntryActivityViewModel
    private lateinit var adapter: CustomAddAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        supportActionBar?.title = "Add Fruit Entry"
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            AddEntryViewModelFactory(apiHelper = ApiHelper(RetrofitBuilder.apiService))
        ).get(AddEntryActivityViewModel::class.java)

        binding.rvList.layoutManager = GridLayoutManager(this, 2)
        adapter = CustomAddAdapter(this)
        binding.rvList.adapter = adapter
        binding.dateInput.setOnClickListener(this)

        viewModel.getFruits().observe(this, { res ->
            res?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { entry ->
                            adapter.fruitList(entry)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, res.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })


    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.dateInput -> {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .build()
                datePicker.show(supportFragmentManager, "Now")
                datePicker.addOnPositiveButtonClickListener {
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.time = Date(it)
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    val formatedDate: String = sdf.format(calendar.time)
                    (binding.dateInput as TextView).text = formatedDate
                    viewModel.addEntry(CreateEntry(formatedDate))
                }
            }
        }
    }


}