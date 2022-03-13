package com.example.myapplication

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: Brain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(Brain::class.java)
        setScrollingTextViews()
    }

    fun buttonClick(view: View) {
        viewModel.processSignal(view.id)
        binding.equationTextView.text = viewModel.equationText
        binding.resultTextView.text = viewModel.resultText
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("equationText", binding.equationTextView.text.toString())
        outState.putString("resultText", binding.resultTextView.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.equationTextView.text = savedInstanceState.getString("equationText")
        binding.resultTextView.text = savedInstanceState.getString("resultText")
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun setScrollingTextViews() {
        binding.equationTextView.movementMethod = ScrollingMovementMethod()
        binding.resultTextView.movementMethod = ScrollingMovementMethod()
    }
}