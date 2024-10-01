package com.example.coleccionprofe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddFrutaActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_fruta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nombreEditText: EditText = findViewById(R.id.nombreEditText)
        val urlImagenEditText: EditText = findViewById(R.id.urlImagenEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val urlImagen = urlImagenEditText.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("nombre", nombre)
            resultIntent.putExtra("urlImagen", urlImagen)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}