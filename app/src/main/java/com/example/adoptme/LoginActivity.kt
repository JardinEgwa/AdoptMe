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
import com.example.adoptme.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    //    viewbinding
    private lateinit var binding: ActivityLoginBinding

    //   ActionBar
    private lateinit var actionBar: ActionBar

    //    ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //    FireBaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

//        configure Actionbar
        actionBar = supportActionBar!!
        actionBar.title = "Login"

//        configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loggin In...")
        progressDialog.setCanceledOnTouchOutside(false)

//        init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

//        handle click, open SignUp
        binding.BtnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

//        handle click, begin Login
        binding.BtnLogin.setOnClickListener {
//      before loggin in validate data
            validateData()
        }
    }

    private fun validateData() {
//        get data
        email = binding.emailLogin.text.toString().trim()
        password = binding.passwordLogin.text.toString().trim()

//        validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            invalid email format
            binding.emailLogin.error = "Invalid Email format"
        } else if (TextUtils.isEmpty(password)) {
//            no password entered
            binding.passwordLogin.error = "Insert Password"
        } else {
//            data is validated
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
//        show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
//                login success
                progressDialog.dismiss()
//                get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Successful logged in as $email", Toast.LENGTH_SHORT).show()
//                open Main Activity
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }
            .addOnFailureListener { e ->
//                login failed
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }


    private fun checkUser() {
//      if user is logged in go to MainActivity
//        get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
//            user logged in
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}





