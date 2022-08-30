package com.example.adoptme


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.adoptme.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    //    ViewBinding
    private lateinit var binding: ActivitySignupBinding

    //   ActionBar
    private lateinit var actionBar: ActionBar

    //    ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //    firebase auth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(binding.root)

//        configure ActionBar, enable back button
        actionBar = supportActionBar!!
        actionBar.title = "SignUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

//        configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating Account In...")
        progressDialog.setCanceledOnTouchOutside(false)

//        init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

//        handle click, begin signup
        binding.SignupBtn.setOnClickListener {
//            validate data
            validateData()
        }

    }

    private fun validateData() {
//        get data
        email = binding.emailSignup.text.toString().trim()
        password = binding.emailSignup.text.toString().trim()

//        validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            invalid email format
            binding.emailSignup.error = "Invalid Email format"
        } else if (TextUtils.isEmpty(password)) {
//            no password entered
            binding.passwordSignup.error = "Insert Password"
        } else {
//            data is validated,sign up
            firebaseSignup()
        }
    }

    private fun firebaseSignup() {
//        show progress
        progressDialog.show()

//        create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
//                sign up success
                progressDialog.dismiss()
//                get current user
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()

//                open MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
//                sign up failed
                progressDialog.dismiss()
                Toast.makeText(this, "SignUp failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //goes back to previous activity when back button is pressed
        return super.onSupportNavigateUp()
    }

    fun gotoLogin(view: View){
//        will open login activity
        val intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

}


