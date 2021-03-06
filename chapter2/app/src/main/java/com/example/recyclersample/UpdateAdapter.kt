package com.example.recyclersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UpdateAdapter: RecyclerView.Adapter<UpdateAdapter.UpdateViewHolder>() {

    var str: String = ""
    /* ViewHolder for displaying header. */
    class UpdateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val updateTextView: TextView = itemView.findViewById(R.id.updateView)

        fun bind(str:String) {
            updateTextView.text = str
        }
    }

    /* Inflates view and returns UpdateViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.update_at, parent, false)
        return UpdateViewHolder(view)
    }

    /* Binds text to the header. */
    override fun onBindViewHolder(holder: UpdateViewHolder, position: Int) {
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