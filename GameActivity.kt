package com.example.numbersguess

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ActionMenuView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import java.util.*

class GameActivity: AppCompatActivity() {
    var max = 0
    private var min = 0
    var halfe = 0
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()
        min = intent.getIntExtra("minimal", 0)
        max = intent.getIntExtra("maximal", 0)
        textView = findViewById(R.id.field)
        halfe = halfeDec(max, min)
        textView.text = halfe.toString()
    }

    fun answerNo(view: android.view.View) {
        if (halfe == min) {
            finishValue(max)
        }
        min = halfe
        halfe = halfeDec(max, min)
        textView.text = halfe.toString()
        Toast.makeText(applicationContext, "Попробуй еще", Toast.LENGTH_SHORT).show()
    }

    fun answerYes(view: android.view.View) {
        Toast.makeText(applicationContext, "Ты победил! ", Toast.LENGTH_SHORT).show()
    }

    private fun finishValue(res: Int) {
        val resultIntent = Intent()
        val putExtra = resultIntent.putExtra("result", res)
        setResult(Activity.RESULT_OK, resultIntent)
        this.finish()
    }

    private fun halfeDec(max: Int, min: Int): Int {
        return (max + min) / 2
    }

}
