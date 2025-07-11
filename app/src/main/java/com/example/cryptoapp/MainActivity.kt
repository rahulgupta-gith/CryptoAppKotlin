package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cryptoapp.adapter.CryptoAdapter
import com.example.cryptoapp.api.BinanceApi
import com.example.cryptoapp.model.Crypto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var adapter : CryptoAdapter
    private lateinit var    recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout:SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchCrypto()


        swipeRefreshLayout.setOnRefreshListener {
            fetchCrypto()
        }


    }
    private fun fetchCrypto(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.binance.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(BinanceApi::class.java)

        api.getPrice().enqueue(object:Callback<List<Crypto>>{
            override fun onResponse(call: Call<List<Crypto>>, response: Response<List<Crypto>>) {
                Log.d("DATA", "Response: ${response.body()}")
                val filtered = response.body()?: emptyList()


                adapter = CryptoAdapter(filtered)
                            recyclerView.adapter = adapter
                            swipeRefreshLayout.isRefreshing = false

            }
            override fun onFailure(call: Call<List<Crypto>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }
        })

    }
}

