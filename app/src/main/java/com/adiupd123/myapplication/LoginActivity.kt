package com.adiupd123.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), OnClickListener{

    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var emailEDT: EditText
    private lateinit var passEDT: EditText
    private lateinit var signInBtn: Button
    private lateinit var forgotBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        emailEDT = findViewById(R.id.email_editText)
        passEDT = findViewById(R.id.password_editText)
        signInBtn = findViewById(R.id.signIn_button)
        forgotBtn = findViewById(R.id.forgotPass_button)
        registerBtn = findViewById(R.id.createNewAccount_button)
        progressBar = findViewById(R.id.progressBar0)
        signInBtn.setOnClickListener(this)
        forgotBtn.setOnClickListener(this)
        registerBtn.setOnClickListener(this)

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("key", 10)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.signIn_button -> signInUser()
            R.id.forgotPass_button -> startActivity(Intent(applicationContext, ForgotPasswordActivity::class.java))
            R.id.createNewAccount_button -> startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }
    public fun signInUser(){
        email = emailEDT.text.toString().trim()
        password = passEDT.text.toString().trim()
        if(email.isEmpty()){
            emailEDT.setError("Email is required!")
            emailEDT.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEDT.setError("Email is invalid!")
            emailEDT.requestFocus()
            return
        }
        if(password.isEmpty()){
            passEDT.setError("Password is required!")
            passEDT.requestFocus()
            return
        }
        if(password.length<6){
            passEDT.setError("Password should be atleast 6 characters long!")
            passEDT.requestFocus()
            return
        }
        progressBar.isVisible = true
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.isVisible = false
                    if (task.isSuccessful) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(applicationContext, task.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
    }
}