package com.example.coleccionprofe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FrutaAdapter(
    private val frutas: List<Fruta>,
    private val onItemClick: (Fruta, Int) -> Unit
) : RecyclerView.Adapter<FrutaAdapter.FrutaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrutaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fruta, parent, false)
        return FrutaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FrutaViewHolder, position: Int) {
        val fruta = frutas[position]
        holder.bind(fruta)
        holder.itemView.setOnClickListener { onItemClick(fruta, position) }
    }

    override fun getItemCount() = frutas.size

    class FrutaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val imagenImageView: ImageView = itemView.findViewById(R.id.imagenImageView)

        fun bind(fruta: Fruta) {
            nombreTextView.text = fruta.nombre
            Glide.with(itemView.context)
                .load(fruta.urlImagen)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(imagenImageView)
        }
    }
}