package com.robgar.books.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.robgar.books.R
import com.robgar.books.databinding.ActivityMainBinding
import com.robgar.books.ui.main.all.AllFragment
import com.robgar.books.ui.main.filter.FilterFragment
import com.robgar.books.ui.maintenance.MaintenanceActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()

        loadFragment()
    }

    private fun setupObserver() {
        viewModel.maintenance.observe(this, {
            if (it == 1) {
                startActivity(Intent(this, MaintenanceActivity::class.java))
                finish()
            }
        })
        viewModel.getMaintenance()
    }

    private fun loadFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, AllFragment.newInstance())
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_fantasy -> replaceFragment(FilterFragment.newInstance(resources.getString(R.string.fantasy_id)))
            R.id.filter_crime -> replaceFragment(FilterFragment.newInstance(resources.getString(R.string.crime_id)))
            R.id.filter_romance -> replaceFragment(FilterFragment.newInstance(resources.getString(R.string.romance_id)))
        }
        return super.onOptionsItemSelected(item)
    }
}