package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: Brain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(Brain::class.java)
    }

    fun buttonClick(view: View) {
        val button = view as Button
        val text = button.text;

        viewModel.processSignal(text.toString())

        val equationText = findViewById<TextView>(R.id.workingsTV)
        equationText.text = viewModel.equationText

        val resultText = findViewById<TextView>(R.id.resultsTV)
        resultText.text = viewModel.resultText
    }


}