/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.recyclersample

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class HotAdapter() :
    RecyclerView.Adapter<HotAdapter.HotViewHolder>() {

    private var hotList: List<HotWord> = arrayListOf<HotWord>()

    // Describes an item view and its place within the RecyclerView
    class HotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context = itemView.context
        private val hotTextView: TextView = itemView.findViewById(R.id.hot_text)
        private val indexTextView: TextView = itemView.findViewById(R.id.index_text)
        private val rightIconView: View = itemView.findViewById(R.id.right_icon)
        private var oldColors: ColorStateList = hotTextView.textColors
        private var currentItem: HotWord? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    itemOnClick()
                }
            }
        }

        private fun itemOnClick() {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("cover_url", currentItem?.cover_url)
            context.startActivity(intent)
        }

        fun bind(position: Int, hotWord: HotWord) {
            currentItem = hotWord
            indexTextView.text = hotWord.position.toString()
            hotTextView.text = hotWord.word
            Log.d("position", hotWord.position.toString() + " " + hotWord.word.toString())
            if (hotWord.position <= 3) {
                indexTextView.setTextColor(Color.YELLOW)
            } else {
                indexTextView.setTextColor(oldColors)
            }
            val visibility = if (hotWord.label == 3) VISIBLE else INVISIBLE
            rightIconView.visibility = visibility
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hot_item, parent, false)

        return HotViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return hotList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: HotViewHolder, position: Int) {
        holder.bind(position, hotList[position])
    }

    fun notifyItems(items: List<HotWord>) {
        hotList = items
        notifyDataSetChanged()
    }
}