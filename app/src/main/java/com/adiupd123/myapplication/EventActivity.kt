package com.adiupd123.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adiupd123.myapplication.databinding.ActivityEventBinding

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}