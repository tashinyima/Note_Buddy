package com.example.notebuddy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notebuddy.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
private lateinit var viewBinder : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = DataBindingUtil.setContentView(this,R.layout.activity_splash)

    }
}