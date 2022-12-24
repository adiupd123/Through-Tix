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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    private lateinit var nameEDT: EditText
    private lateinit var birthdayEDT: EditText
    private lateinit var emailEDT: EditText
    private lateinit var usernameEDT: EditText
    private lateinit var mobileEDT: EditText
    private lateinit var newpasswordEDT: EditText
    private lateinit var reenterpasswordEDT: EditText
    private lateinit var signUpButton: Button
    private lateinit var progressBar1: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("users")
        nameEDT = findViewById(R.id.name_editText)
        birthdayEDT = findViewById(R.id.birthday_editText)
        emailEDT = findViewById(R.id.emailID_editText)
        usernameEDT = findViewById(R.id.mobile_editText)
        newpasswordEDT = findViewById(R.id.newPassword_editText)
        reenterpasswordEDT = findViewById(R.id.reenterPass_editText)
        progressBar1 = findViewById(R.id.progressBar1)
        signUpButton = findViewById(R.id.signUp_button)
        signUpButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.signUp_button->{
                registerUser()
            }
            R.id.signIn_button->{
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        }
    }
    public fun registerUser(){
        val name: String = nameEDT.getText().toString().trim { it <= ' ' }
        val birthday: String = birthdayEDT.getText().toString().trim { it <= ' ' }
        val mobileNo: String = mobileEDT.getText().toString().trim { it <= ' ' }
        val emailID: String = emailEDT.getText().toString().trim { it <= ' ' }
        val username: String = usernameEDT.getText().toString().trim { it <= ' ' }
        val newPassword: String = newpasswordEDT.getText().toString().trim { it <= ' ' }
        val reNewPassword: String = reenterpasswordEDT.getText().toString().trim { it <= ' ' }

        if (name.isEmpty()) {
            nameEDT.setError("Name is Required!")
            nameEDT.requestFocus()
            return
        }
        if (birthday.isEmpty()) {
            birthdayEDT.setError("Birthday is Required!")
            birthdayEDT.requestFocus()
            return
        }
        if (mobileNo.isEmpty()) {
            mobileEDT.setError("Mobile no. is Required!")
            mobileEDT.requestFocus()
            return
        }
        if (emailID.isEmpty()) {
            // Also check whether the emailID is present in the database already or not
            emailEDT.setError("Email is Required!")
            emailEDT.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()) {
            emailEDT.setError("Email is Invalid!")
            emailEDT.requestFocus()
            return
        }
        if (username.isEmpty()) {
            usernameEDT.setError("Username is Required!")
            usernameEDT.requestFocus()
            return
        }
        if (username.length > 20) {
            usernameEDT.setError("Max. length of username: 20")
            usernameEDT.requestFocus()
            return
        }
        if (username.length < 6) {
            usernameEDT.setError("Min. length of username: 6")
            usernameEDT.requestFocus()
            return
        }
        if (newPassword.isEmpty()) {
            newpasswordEDT.setError("Password is Required!")
            newpasswordEDT.requestFocus()
            return
        }
        if (newPassword.length < 6) {
            newpasswordEDT.setError("Min. length of Password: 6")
            newpasswordEDT.requestFocus()
            return
        }
        if (newPassword.length > 30) {
            newpasswordEDT.setError("Min. length of Password: 6")
            newpasswordEDT.requestFocus()
            return
        }
        if (newPassword != reNewPassword) {
            reenterpasswordEDT.setError("Re-entered password does not match original password")
            reenterpasswordEDT.requestFocus()
            return
        }
        val mp = mutableMapOf<String, String>(
            "name" to name,
            "birthday" to birthday,
            "mobile_no" to mobileNo,
            "username" to username,
            "email" to emailID
        )
        val userKey: String = ref.push().key.toString()
        ref.child(userKey).child(username).setValue(mp)

        progressBar1.isVisible = true

        mAuth.createUserWithEmailAndPassword(emailID, newPassword)
            .addOnCompleteListener(this) { task ->
                progressBar1.setVisibility(View.GONE)
                if (task.isSuccessful) {
                    ref.child(username).setValue(mp).addOnCompleteListener(
                        OnCompleteListener<Void?> {
                            Toast.makeText(
                                applicationContext,
                                "Your account is created.",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(
                            applicationContext,
                            "You're already registered",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

    }
}