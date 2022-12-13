package com.example.notebuddy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notebuddy.databinding.ActivityMainBinding
import com.example.notebuddy.view.NotesViewModels
import com.example.notebuddy.view.ui.alarm.AlarmFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //private val viewModel: NotesViewModels by viewModels()
    private lateinit var viewBinder: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initToolbar()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragHomeContainer) as NavHostFragment
        navController = navHostFragment.navController

        val botomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        // links navcontroller with bottom navigation view
        botomNav.setupWithNavController(navController)
        // sets up actionbar with nav see title changes
       // setupActionBarWithNavController(navController)
    }

    private fun initToolbar() {
        val toolbar = viewBinder.toolBarLayout.toolbar
        toolbar.title= "Note Buddy"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.sort_menu -> {



            }
        }
        return super.onOptionsItemSelected(item)
    }




}