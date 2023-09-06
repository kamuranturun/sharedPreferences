package com.turun.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.turun.sharedpreferences.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // ViewBinding nesnesini tanımlama
    private lateinit var binding: ActivityMainBinding


    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding nesnesini oluşturma
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()

        sharedPreferences = getSharedPreferences("myp", Context.MODE_PRIVATE)


        binding.btButton.setOnClickListener {

            binding.swSwitch.isChecked=!binding.swSwitch.isChecked
            saveData()
        }
    }


    private fun saveData() {

        val insertedText = binding.etText.text.toString()
        binding.tvText.text = insertedText
        val sharedPreferences = getSharedPreferences("shp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("SK", insertedText)
            putBoolean("BK", binding.swSwitch.isChecked)
        }.apply()
        Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show()

    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shp", Context.MODE_PRIVATE)

        val savedString = sharedPreferences.getString("SK", null)
        val savedBoolean = sharedPreferences.getBoolean("BK", false)

        binding.tvText.text = savedString
        binding.swSwitch.isChecked = savedBoolean

    }

}