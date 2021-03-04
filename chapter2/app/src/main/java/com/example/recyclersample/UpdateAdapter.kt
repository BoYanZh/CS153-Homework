package com.example.recyclersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UpdateAdapter: RecyclerView.Adapter<UpdateAdapter.HeaderViewHolder>() {

    var str: String = ""
    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val updateTextView: TextView = itemView.findViewById(R.id.updateView)

        fun bind(str:String) {
            updateTextView.text = str
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.update_at, parent, false)
        return HeaderViewHolder(view)
    }

    /* Binds number of flowers to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(str)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    fun updateStr(updateAt: String){
        str = updateAt
    }
}