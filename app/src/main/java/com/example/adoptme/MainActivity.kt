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
import com.example.adoptme.model.CategoryItem
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

//data for model class
//        first category
        val categoryItemList: MutableList<CategoryItem> = ArrayList()
        categoryItemList.add(CategoryItem(1,R.drawable.beagle))
        categoryItemList.add(CategoryItem(1,R.drawable.dog2))
        categoryItemList.add(CategoryItem(1,R.drawable.dog3))
        categoryItemList.add(CategoryItem(1,R.drawable.dog4))
        categoryItemList.add(CategoryItem(1,R.drawable.dog5))

        //        second category
        val categoryItemList2: MutableList<CategoryItem> = ArrayList()
        categoryItemList2.add(CategoryItem(1,R.drawable.birman))
        categoryItemList2.add(CategoryItem(1,R.drawable.siamese))
        categoryItemList2.add(CategoryItem(1,R.drawable.occicat))
        categoryItemList2.add(CategoryItem(1,R.drawable.mainecoon))
        categoryItemList2.add(CategoryItem(1,R.drawable.sibeariancat))

        //        third category
        val categoryItemList3: MutableList<CategoryItem> = ArrayList()
        categoryItemList3.add(CategoryItem(1,R.drawable.budgie))
        categoryItemList3.add(CategoryItem(1,R.drawable.canary))
        categoryItemList3.add(CategoryItem(1,R.drawable.dove))
        categoryItemList3.add(CategoryItem(1,R.drawable.finch))
        categoryItemList3.add(CategoryItem(1,R.drawable.macaw))

        //        fourth category
        val categoryItemList4: MutableList<CategoryItem> = ArrayList()
        categoryItemList4.add(CategoryItem(1,R.drawable.dogcollar))
        categoryItemList4.add(CategoryItem(1,R.drawable.dogleash))
        categoryItemList4.add(CategoryItem(1,R.drawable.litterbox))
        categoryItemList4.add(CategoryItem(1,R.drawable.birdcage))

        val allCategory: MutableList<AllCategory> = ArrayList()
        allCategory.add(AllCategory("Dogs",categoryItemList))
        allCategory.add(AllCategory("Cats",categoryItemList2))
        allCategory.add(AllCategory("Birds",categoryItemList3))
        allCategory.add(AllCategory("Accessories",categoryItemList4))

        setMainCategoryRecycler(allCategory)

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

