package com.example.coleccionprofe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditFrutaActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var urlImagenEditText: EditText
    private lateinit var deleteButton: Button
    private lateinit var saveButton: Button

    private var position: Int = -1 // Para identificar el ítem seleccionado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_fruta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializamos las vistas
        nombreEditText = findViewById(R.id.nombreEditText)
        urlImagenEditText = findViewById(R.id.urlImagenEditText)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)

        // Obtenemos los datos enviados desde la MainActivity
        val nombre = intent.getStringExtra("nombre")
        val urlImagen = intent.getStringExtra("urlImagen")
        position = intent.getIntExtra("position", -1)

        // Mostramos los valores en los EditTexts
        nombreEditText.setText(nombre)
        urlImagenEditText.setText(urlImagen)

        // Botón para guardar los cambios
        saveButton.setOnClickListener {
            val nuevoNombre = nombreEditText.text.toString()
            val nuevaUrlImagen = urlImagenEditText.text.toString()

            if (nuevoNombre.isNotEmpty() && nuevaUrlImagen.isNotEmpty()) {
                // Retornamos los datos actualizados a la MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("nuevoNombre", nuevoNombre)
                resultIntent.putExtra("nuevaUrlImagen", nuevaUrlImagen)
                resultIntent.putExtra("position", position)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para eliminar el ítem
        deleteButton.setOnClickListener {
            // Retornamos la posición para eliminar el ítem en la MainActivity
            val resultIntent = Intent()
            resultIntent.putExtra("delete", true)
            resultIntent.putExtra("position", position)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}