package com.adiupd123.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.adiupd123.myapplication.databinding.ActivityMainBinding
import com.airbnb.paris.extensions.backgroundTint
import com.airbnb.paris.extensions.style
import com.airbnb.paris.extensions.textColor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allButton.setOnClickListener {
            Toast.makeText(applicationContext, "All Content", Toast.LENGTH_SHORT).show()
        }

        binding.comedyButton.setOnClickListener {
            Toast.makeText(applicationContext, "Comedy Shows", Toast.LENGTH_SHORT).show()
        }
        binding.playsButton.setOnClickListener {
            Toast.makeText(applicationContext, "Plays", Toast.LENGTH_SHORT).show()
        }
        binding.moviesButton.setOnClickListener {
            Toast.makeText(applicationContext, "Movies", Toast.LENGTH_SHORT).show()
        }

    }
}
