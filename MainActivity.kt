package com.example.numbersguess

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var min = 0
    private var max = 0
    private lateinit var textViewResult: TextView

    @SuppressLint("SetTextI18n")
    private val gameIntentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = result.data
                val result = intent?.getIntExtra("result", 0)
                textViewResult.text = getString(R.string.resultnumber) + result
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        textViewResult = findViewById(R.id.results)
        textViewResult.text = getString(R.string.main_text)
    }

    fun onStartGameClick(view: android.view.View) {

        min = 0
        max = 0

        val upperEdit = findViewById<TextView>(R.id.NumberUp)
        val lowerEdit = findViewById<TextView>(R.id.NumberLower)

        val inputU = upperEdit?.text.toString().trim()
        val inputL = lowerEdit?.text.toString().trim()

        if (inputU.isNotBlank() && inputL.isNotBlank()) {
            min = inputL.toInt()
            max = inputU.toInt()
        }

        if (max > min) {

            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("minimal", min)
            intent.putExtra("maximal", max)
            gameIntentLauncher.launch(intent)
        } else {
            exitProcess(1)
        }
    }
}
