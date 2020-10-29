package com.robgar.books.ui.maintenance

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robgar.books.databinding.ActivityMaintenanceBinding
import com.robgar.books.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MaintenanceActivity : AppCompatActivity() {

    private val viewModel: MaintenanceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMaintenanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.maintenance.observe(this, {
            if (it == 0) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
        viewModel.getMaintenance()
    }
}