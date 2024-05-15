package com.example.labone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var editTxt: EditText
    lateinit var Btn: Button
    lateinit var myRecycle: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTxt = findViewById(R.id.editTxt)
        Btn = findViewById(R.id.search)
        myRecycle = findViewById(R.id.rv)
        progressBar = findViewById(R.id.progressBar)
        image = findViewById(R.id.image)
        myRecycle.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val allFunctions = RetroClient().getRetrofitClients()
        allFunctions.getUsers().enqueue(
            object : Callback<UsersResponse> {
                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.size == 0) {
                            myRecycle.isVisible = false
                            progressBar.isVisible = false
                            image.isVisible = true
                            return
                        }
                        myRecycle.isVisible = true
                        progressBar.isVisible = false
                        image.isVisible = false
                        myRecycle.adapter = CustomAdapter(response.body())
                    } else {
                        myRecycle.isVisible = false
                        progressBar.isVisible = false
                        image.isVisible = true
                        Toast.makeText(this@MainActivity, "Something Wrong Happened!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    myRecycle.isVisible = false
                    progressBar.isVisible = false
                    image.isVisible = true
                    Toast.makeText(this@MainActivity, "Failed to Load", Toast.LENGTH_SHORT).show()
                }

            }
        )
        Btn.setOnClickListener {
            var inputValue = editTxt.text.toString()
            if (inputValue.isEmpty()) {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            allFunctions.getUsersById(inputValue).enqueue(
                object : Callback<UsersResponse> {
                    override fun onResponse(
                        call: Call<UsersResponse>,
                        response: Response<UsersResponse>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()!!.size == 0) {
                                myRecycle.isVisible = false
                                progressBar.isVisible = false
                                image.isVisible = true
                                return
                            }
                            myRecycle.isVisible = true
                            progressBar.isVisible = false
                            image.isVisible = false
                            myRecycle.adapter = CustomAdapter(response.body())
                        }
                    }

                    override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                        myRecycle.isVisible = false
                        progressBar.isVisible = false
                        image.isVisible = true
                        Toast.makeText(this@MainActivity, "Failed to Load", Toast.LENGTH_SHORT).show()
                    }

                }
            )
        }
    }
}