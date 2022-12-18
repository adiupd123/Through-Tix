package com.adiupd123.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.account_item -> Toast.makeText(applicationContext,"account_item", Toast.LENGTH_SHORT).show()
            R.id.tickets_item -> Toast.makeText(applicationContext,"tickets_item", Toast.LENGTH_SHORT).show()
            R.id.contact_item -> Toast.makeText(applicationContext,"contact_item", Toast.LENGTH_SHORT).show()
            R.id.logout_item -> Toast.makeText(applicationContext,"logout_item", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
