package com.example.mockijson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.lang.StringBuilder
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tvShowRecepit)

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val ingredientsList = obj.getJSONArray("ingredients")

            for (i in 0 until ingredientsList.length()) {
                val obj2 = ingredientsList.getJSONObject(i)
                val itemList = obj2.getJSONArray("item")
                val title = obj2.getString("title")
                Log.d("MainActivityState", "Title: $title")
                Log.d("MainActivityState", "IngredientList Size = ${ingredientsList.length()}")
                for (j in 0 until itemList.length()) {
                    val data = itemList.getJSONObject(j)
                    Log.d("MainActivityState", data.getString("ingredient"))
//                    Log.d("MainActivityState", itemList.length().toString())
                    //Komentar
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getJSONFromAssets(): String? {
        var json: String? = null
        val charset: Charset = Charsets.UTF_8

        try {
            val `is` = assets.open("Ingredient.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}