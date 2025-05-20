package com.example.inputvalidation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var errorTextView: TextView
    private lateinit var lengthButton: Button
    private lateinit var reverseButton: Button
    private lateinit var appendButton: Button
    private lateinit var numericButton: Button
    private lateinit var clearButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputEditText = findViewById(R.id.inputEditText)
        outputTextView = findViewById(R.id.outputTextView)
        errorTextView = findViewById(R.id.errorTextView)
        lengthButton = findViewById(R.id.lengthButton)
        reverseButton = findViewById(R.id.reverseButton)
        appendButton = findViewById(R.id.appendButton)
        numericButton = findViewById(R.id.numericButton)
        clearButton = findViewById(R.id.clearButton)

        lengthButton.setOnClickListener {
            if (validateInput()) {
                val input = inputEditText.text.toString().trim()
                outputTextView.text = if (input.isEmpty()) "0" else input.length.toString()
            }
        }

        reverseButton.setOnClickListener {
            if (validateInput()) {
                val input = inputEditText.text.toString()
                outputTextView.text = if (input.trim().isEmpty()) "->" else input.reversed()
            }
        }

        appendButton.setOnClickListener {
            if (validateInput()) {
                val input = inputEditText.text.toString()
                outputTextView.text = "Hello $input"
            }
        }

        numericButton.setOnClickListener {
            if (validateInput()) {
                val input = inputEditText.text.toString().trim()
                outputTextView.text = if (input.matches(Regex("\\d+"))) "Yes" else "No"
            }
        }

        clearButton.setOnClickListener {
            inputEditText.text.clear()
            outputTextView.text = ""
            errorTextView.text = ""
        }
    }


    private fun validateInput(): Boolean {
        val input = inputEditText.text.toString()
        return if (!input.matches(Regex("[a-zA-Z0-9 ]*"))) {
            errorTextView.text = "Only alphanumeric characters and spaces are allowed."
            outputTextView.text = ""
            false
        } else {
            errorTextView.text = ""
            true
        }
    }
}