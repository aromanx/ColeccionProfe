package com.example.coleccionprofe

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var frutaAdapter: FrutaAdapter
    private val frutas = mutableListOf(
        Fruta("Manzana", "https://freepngimg.com/thumb/apple/16-red-apple-png-image.png"),
        Fruta("Plátano", "https://pngimg.com/uploads/banana/banana_PNG827.png"),
        Fruta("Naranja", "https://pngimg.com/uploads/orange/orange_PNG780.png"),
        Fruta("Pera", "https://www.pngarts.com/files/3/Pear-Free-PNG-Image.png")
    )
    private val addFrutaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val nombre = data?.getStringExtra("nombre")
            val urlImagen = data?.getStringExtra("urlImagen")
            if (nombre != null && urlImagen != null) {
                frutas.add(Fruta(nombre, urlImagen))
                frutaAdapter.notifyItemInserted(frutas.size - 1)
            }
        }
    }

    private val editFrutaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val position = data?.getIntExtra("position", -1) ?: -1
            if (position != -1) {
                if (data?.getBooleanExtra("delete", false) == true) {
                    frutas.removeAt(position)
                    frutaAdapter.notifyItemRemoved(position)
                } else {
                    val nuevoNombre = data?.getStringExtra("nuevoNombre")
                    val nuevaUrlImagen = data?.getStringExtra("nuevaUrlImagen")
                    if (nuevoNombre != null && nuevaUrlImagen != null) {
                        frutas[position] = Fruta(nuevoNombre, nuevaUrlImagen)
                        frutaAdapter.notifyItemChanged(position)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        frutaAdapter = FrutaAdapter(frutas) { fruta, position ->
            val intent = Intent(this, EditFrutaActivity::class.java)
            intent.putExtra("nombre", fruta.nombre)
            intent.putExtra("urlImagen", fruta.urlImagen)
            intent.putExtra("position", position)
            editFrutaLauncher.launch(intent)
        }

        recyclerView.adapter = frutaAdapter

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddFrutaActivity::class.java)
            addFrutaLauncher.launch(intent)
        }
    }
}