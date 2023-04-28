package com.example.rgblista

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RGBAdapter(val lista: MutableList<RGB>): RecyclerView.Adapter<RGBAdapter.MyHolder>() {
    var onItemClickRecyclerView: OnItemClickRecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RGBAdapter.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: RGBAdapter.MyHolder, position: Int) {
        val rgb = this.lista[position]
        holder.colorName.text = rgb.name.toString()
        holder.tvRed.text = "Vermelho: " + rgb.red.toString()
        holder.tvGreen.text = "Verde: " + rgb.green.toString()
        holder.tvBlue.text = "Azul: " + rgb.blue.toString()
        DrawableCompat.setTint(holder.ivIcon.drawable, Color.rgb(rgb.red, rgb.green, rgb.blue))
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    fun add(rgb: RGB){
        this.lista.add(rgb)
        this.notifyItemInserted(this.lista.size)
    }

    fun del(index: Int) {
        this.lista.removeAt(index)
        this.notifyItemRemoved(index)
        this.notifyItemRangeChanged(index, this.lista.size)
    }

    fun move(from: Int, to: Int) {
        Collections.swap(this.lista, from, to)
        this.notifyItemMoved(from, to)
    }

    inner class MyHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var colorName: TextView
        var tvRed: TextView
        var tvGreen: TextView
        var tvBlue: TextView
        var ivIcon: ImageView
        init {
            this.colorName = itemView.findViewById(R.id.colorName)
            this.tvRed = itemView.findViewById(R.id.tvRed)
            this.tvGreen = itemView.findViewById(R.id.tvGreen)
            this.tvBlue = itemView.findViewById(R.id.tvBlue)
            this.ivIcon = itemView.findViewById(R.id.imageView)
            itemView.setOnClickListener{
                this@RGBAdapter.onItemClickRecyclerView?.onItemClick(this.adapterPosition)
            }
        }
    }
}