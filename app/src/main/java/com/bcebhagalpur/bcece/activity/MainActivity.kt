package com.bcebhagalpur.bcece.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bcebhagalpur.bcece.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mAuth: FirebaseAuth

    //UI elements
    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvEmailVerified:TextView
    private lateinit var btnSignOut:Button
    private lateinit var imgPicasso: ImageView
    private lateinit var btnLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSignOut=findViewById(R.id.btnSignOut)
        imgPicasso=findViewById(R.id.imgPicasso)
        btnLogin=findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener { startActivity(Intent(this,
            LoginActivity::class.java)) }
        btnSignOut.setOnClickListener { startActivity(Intent(this,SwipActivity::class.java)) }
        btnHome.setOnClickListener { startActivity(Intent(this,
            HomeActivity::class.java)) }
        btnCollege.setOnClickListener {startActivity(Intent(this,Main2Activity::class.java))}

        val url1="https://upload.wikimedia.org/wikipedia/en/3/37/Motihari_College_of_Engineering_logo.jpg"
            Picasso.get().load(url1).error(R.drawable.developer_image).into(imgPicasso)
        initialise()

    }

    private fun initialise() {
        mDatabase =FirebaseDatabase.getInstance()
        mDatabaseReference =mDatabase.reference.child("Users")
        mAuth =FirebaseAuth.getInstance()
        tvFirstName =findViewById(R.id.tv_first_name)
        tvLastName =findViewById(R.id.tv_last_name)
        tvEmail =findViewById(R.id.tv_email)
        tvEmailVerified =findViewById(R.id.tv_email_verifiied)
    }


        override fun onStart() {
                super.onStart()
                val mUser = mAuth.currentUser
                val mUserReference = mDatabaseReference.child(mUser!!.uid)

                tvEmail.text = mUser.email
                tvEmailVerified.text = mUser.isEmailVerified.toString()

                mUserReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        tvFirstName.text = snapshot.child("firstName").value as String
                        tvLastName.text  = snapshot.child("lastName"). value as String

//                        btnSignOut.setOnClickListener { tvFirstName.text=snapshot.child("").value as String
//                        tvLastName.text=snapshot.child("").value as String
//                        mAuth.signOut() }

                    }
                    override fun onCancelled(databaseError: DatabaseError) {}
                })
            }
}
