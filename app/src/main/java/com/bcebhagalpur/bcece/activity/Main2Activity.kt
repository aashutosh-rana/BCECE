package com.bcebhagalpur.bcece.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bcebhagalpur.bcece.R
import com.bcebhagalpur.bcece.adapter.CollegeListAdapter
import com.bcebhagalpur.bcece.model.College
import com.bcebhagalpur.bcece.util.ConnectionManager
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONException

class Main2Activity : AppCompatActivity() {

    private lateinit var recyclerCollege: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerAdapter: CollegeListAdapter
    private val collegeInfoInfoList = arrayListOf<College>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerCollege = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)

        val queue = Volley.newRequestQueue(this)

        val url = "https://c-aashutosh-2001.000webhostapp.com/cInformation5.json"

        if (ConnectionManager().checkConnectivity(this)){
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
                try {
//                    val data=it.getJSONObject("data")
//                    val success=it.getBoolean("success")
//                    if (success){
                    val collegeArray=it.getJSONArray("data")
                    for (i in 0 until collegeArray.length()){
                        val collegeJsonObject = collegeArray.getJSONObject(i)
                        val collegeObject = College(
                            collegeJsonObject.getString("id"),
                                collegeJsonObject.getString("name"),
                            collegeJsonObject.getString("image"),
                            collegeJsonObject.getString("about"),
                            collegeJsonObject.getString("pimage"),
                            collegeJsonObject.getString("pname"),
                            collegeJsonObject.getString("pemail"),
                            collegeJsonObject.getString("afliation")
                        )
                        collegeInfoInfoList.add(collegeObject)
                        recyclerAdapter = CollegeListAdapter(this, collegeInfoInfoList)
                        recyclerView.adapter = recyclerAdapter
                        recyclerView.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)


                        println("response1 is $it")

                    }
//                    }else {
//                        Toast.makeText(activity as Context, "Some Error Occurred!1", Toast.LENGTH_SHORT).show()
//                        println("response2 is $it")
//                    }

                }catch(e: JSONException){
                   Toast.makeText(this, "Some Error Occurred!2", Toast.LENGTH_SHORT).show()
                    println("response3 is $it")
                }

            }, Response.ErrorListener {
                    Toast.makeText(this, "Some Error Occurred!3", Toast.LENGTH_SHORT) .show()
                println("response4 is $it")
            }
            )
            queue.add(jsonObjectRequest)

        }else{
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection is not Found")
            dialog.setPositiveButton("Open Settings"){ _, _ ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                this.finish()
            }

            dialog.setNegativeButton("Exit") { _, _ ->
                ActivityCompat.finishAffinity(this)
            }
            dialog.create()
            dialog.show()
        }
    }
    }

