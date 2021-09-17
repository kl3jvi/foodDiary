package com.kl3jvi.fooddiary.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.MaterialDatePicker
import com.kl3jvi.fooddiary.R

import com.kl3jvi.fooddiary.databinding.ActivityMainBinding
import com.kl3jvi.fooddiary.model.entities.entries.CreateEntry
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.network.RetrofitBuilder
import com.kl3jvi.fooddiary.view.fragments.MyDiaryFragment
import com.kl3jvi.fooddiary.viewmodel.AddEntryActivityViewModel
import com.kl3jvi.fooddiary.viewmodel.AddEntryViewModelFactory
import java.text.SimpleDateFormat
import java.util.*





class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AddEntryActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            AddEntryViewModelFactory(apiHelper = ApiHelper(RetrofitBuilder.apiService))
        ).get(AddEntryActivityViewModel::class.java)


        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.fab.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Create New Entry")
                    .build()
            datePicker.show(supportFragmentManager, "Now")
            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.time = Date(it)
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val formatedDate: String = sdf.format(calendar.time)
                viewModel.addEntry(CreateEntry(formatedDate))
                (supportFragmentManager.findFragmentById(R.id.navigation_home) as MyDiaryFragment?)?.observeEntries()
            }
        }
    }
}