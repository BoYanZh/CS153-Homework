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

import android.content.Context
import android.os.StrictMode
import org.json.JSONObject
import java.net.URL


class Datasource(val context: Context) {
    private var jsonObj = JSONObject();

    init {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val jsonString = URL("https://aweme-hl.snssdk.com/aweme/v1/hot/search/list/").readText()
        jsonObj = JSONObject(jsonString)
    }

    fun getHotList(): List<HotWord> {
        val res: ArrayList<HotWord> = arrayListOf<HotWord>()
        val data = jsonObj.getJSONObject("data")
        val list = data.getJSONArray("word_list")
        for (i in 0 until list.length()) {
            val item = list.getJSONObject(i)
            res.add(
                HotWord(
                    item.getInt("position"),
                    item.getString("word"),
                    item.getInt("label"),
                    item.getJSONObject("word_cover").getJSONArray("url_list").getString(0)
                )
            )
        }
        return res.sortedWith(compareBy { it.position })
    }

    fun getLastUpdate(): String {
        val data = jsonObj.getJSONObject("data")
        return data.getString("active_time")
    }
}