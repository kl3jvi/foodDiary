package com.kl3jvi.fooddiary.view.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kl3jvi.fooddiary.R
import com.kl3jvi.fooddiary.databinding.ActivityAddEntryBinding
import com.kl3jvi.fooddiary.model.entities.entries.EntriesItem
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.network.RetrofitBuilder
import com.kl3jvi.fooddiary.utils.Constants
import com.kl3jvi.fooddiary.utils.Status
import com.kl3jvi.fooddiary.view.adapters.CustomAddAdapter
import com.kl3jvi.fooddiary.viewmodel.AddEntryActivityViewModel
import com.kl3jvi.fooddiary.viewmodel.AddEntryViewModelFactory


class AddEntryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddEntryBinding
    private lateinit var viewModel: AddEntryActivityViewModel
    private lateinit var adapter: CustomAddAdapter
    private lateinit var mEntryItem: EntriesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        supportActionBar?.title = "Add Fruit Entry"
        setContentView(binding.root)

        if (intent.hasExtra(Constants.EXTRA_DETAILS)) {
            mEntryItem = intent.getParcelableExtra(Constants.EXTRA_DETAILS)!!
            supportActionBar?.title = getString(R.string.edit_title)
        }


        mEntryItem.fruit.forEach {

        }

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

    }


}