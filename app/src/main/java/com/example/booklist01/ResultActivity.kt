package com.example.booklist01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array

class ResultActivity : AppCompatActivity() {

    private val TAG: String = "CHECK"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        Log.d(TAG,"onCreated")
        val searchKey = intent.getStringExtra("SEARCH_KEY")

        val ss = "volumes?q=$searchKey"
        val mRecyclerView = findViewById<RecyclerView>(R.id.recyclerViewRes)

        val retrofit = Retrofit.Builder().
            baseUrl("https://www.googleapis.com/books/").
            addConverterFactory(GsonConverterFactory.create()).
            build()
        val apiBook: Api = retrofit.create(Api::class.java)
        Log.d(TAG,ss)
        val call = apiBook.getBookSearchRes(searchKey)
        //val call =apiBook.books
        Log.d(TAG,"Before enqueue")
        call.enqueue(object : Callback<BooksT> {
            override fun onFailure(call: Call<BooksT>, t: Throwable) {
                Log.d(TAG,"onFailure")
                Log.d(TAG,t.message.toString())
                Toast.makeText(this@ResultActivity,"FAiled", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<BooksT>, response: Response<BooksT>) {
                Log.d(TAG,"onResponse called ")
                val booksList = response.body()
                Log.d(TAG,"${booksList!!.totalItems} items size: ${booksList!!.items.size}")
                if(response.isSuccessful)
                {
                    mRecyclerView.layoutManager = LinearLayoutManager(this@ResultActivity)
                    mRecyclerView.adapter = RecyclerViewAdapter(this@ResultActivity, booksList!!.items)
                }

            }
        }
        )


        Log.d(TAG,"After enqueue")

    }
}
