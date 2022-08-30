package com.example.adoptme

import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptme.adapter.MainRecyclerAdapter
import com.example.adoptme.databinding.ActivityMainBinding
import com.example.adoptme.model.AllCategory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var mainCategoryRecycler : RecyclerView? = null
     private var mainRecyclerAdapter : MainRecyclerAdapter?= null

    //    viewbinding
    private lateinit var binding: ActivityMainBinding

    //    ActionBar
    private lateinit var actionBar: ActionBar

    //    Firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

//

//Configure Action Bar
        actionBar = supportActionBar!!
        actionBar.title = "AdoptMe"

//        init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
    }

    private fun setMainCategoryRecycler(allCategory: List<AllCategory>){
        mainCategoryRecycler = findViewById(R.id.main_recycler)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = MainRecyclerAdapter(this,allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }



    private fun checkUser() {
//        check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
//            user not null, user logged in, get user info
            val email = firebaseUser.email
            binding.tvEmail.text = email

        } else {
//            user is null, user not logged in therefore log them in
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

//    private fun replacefragment(dogsFragment: DogsFragment) {
//
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frame_layout,dogsFragment)
//        fragmentTransaction.commit()
//
//    }
//
//    private fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.frame_layout,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
