package com.bcebhagalpur.bcece.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.bcece.R
import com.bcebhagalpur.bcece.activity.Main3Activity
import com.bcebhagalpur.bcece.model.College
import com.squareup.picasso.Picasso

class CollegeListAdapter(private val context: Context, private val itemList: ArrayList<College>) : RecyclerView.Adapter<CollegeListAdapter.CollegeNameViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeNameViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.college_list_recycler_row, parent, false)
         return CollegeNameViewHolder(view)

     }
     override fun getItemCount(): Int {
         return itemList.size
     }
     override fun onBindViewHolder(holder: CollegeNameViewHolder, position: Int) {

         val college = itemList[position]
         holder.txtCollegeName.text = college.Name
        Picasso.get().load(college.Image).error(R.drawable.developer_image).into(holder.imgCollegeImage)
         holder.txtAboutCollege.text=college.about
         holder.txtPrincipalName.text=college.pname
         Picasso.get().load(college.pimage).error(R.drawable.developer_image).into(holder.imgPrincipal)
         holder.txtEmail.text=college.pemail
         holder.txtAffiliation.text=college.afliation
         holder.llContent.setOnClickListener {
             val intent = Intent(context, Main3Activity::class.java)
             context.startActivity(intent)
         }

     }
     class CollegeNameViewHolder(view: View) : RecyclerView.ViewHolder(view){
         val txtCollegeName: TextView = view.findViewById(R.id.txtCollegeName)
         val imgCollegeImage: ImageView = view.findViewById(R.id.imgCollege)
         val txtAboutCollege:TextView=view.findViewById(R.id.txtAboutCollege)
         val txtPrincipalName:TextView = view.findViewById(R.id.txtPrincipleName)
         val imgPrincipal:ImageView=view.findViewById(R.id.imgPrinciple)
         val txtEmail:TextView=view.findViewById(R.id.txtEmail)
         val txtAffiliation:TextView=view.findViewById(R.id.txtAffiliation)
         val llContent: RelativeLayout = view.findViewById(R.id.relativeLayout)
     }
 }