package com.bcebhagalpur.bcece.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bcebhagalpur.bcece.R
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    //global variables
    private lateinit var email: String
    private lateinit var password: String

    //UI elements
    private lateinit var tvForgotPassword: TextView
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    private lateinit var mProgressBar: ProgressDialog

    //Firebase references
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialise()
    }

    private fun initialise() {
        tvForgotPassword =findViewById(R.id.tv_forgot_password)
        etEmail =findViewById(R.id.et_email)
        etPassword =findViewById(R.id.et_password)
        btnLogin =findViewById(R.id.btn_login)
        btnCreateAccount =findViewById(R.id.btn_register_account)
        mProgressBar = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
        tvForgotPassword
            .setOnClickListener { startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            ) }
        btnCreateAccount
            .setOnClickListener { startActivity(Intent(this@LoginActivity,
                RegistrationActivity::class.java)) }
        btnLogin.setOnClickListener { loginUser() }
    }

    private fun loginUser() {

        email = etEmail.text.toString()
        password = etPassword.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mProgressBar.setMessage("Logging User...")
            mProgressBar.show()

            Log.d(TAG, "Logging in user.")

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    mProgressBar.hide()

                    if (task.isSuccessful) {
                        // Sign in success, update UI with signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Password and Email doesn't match",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateUI() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
