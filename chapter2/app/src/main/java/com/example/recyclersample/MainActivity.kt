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

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ConcatAdapter

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Retrieves data from datasource
        val hotList = Datasource(this).getHotList()
        val hotAdapter = HotAdapter()
        hotAdapter.notifyItems(hotList)
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (hotList.contains(query)) {
                    hotAdapter.notifyItems(hotList.filter { s -> s.word.contains(query) })
                } else {
                    Toast.makeText(this@MainActivity, "No Match found", Toast.LENGTH_LONG).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                hotAdapter.notifyItems(hotList.filter { s -> s.word.contains(newText) })
                return false
            }
        })
        val updateAdapter = UpdateAdapter()
        updateAdapter.updateStr("更新于:" + Datasource(this).getLastUpdate())
        val concatAdapter = ConcatAdapter(updateAdapter, hotAdapter)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = concatAdapter
    }
}
