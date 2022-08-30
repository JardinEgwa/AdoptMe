package com.example.adoptme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptme.R
import com.example.adoptme.model.AllCategory

class MainRecyclerAdapter(
    private val context:Context, private val allCategory:List <AllCategory>):
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {


    class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private var categoryTitle : TextView? = null
        init{
            categoryTitle = itemView.findViewById(R.id.cat_title)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item,parent,false))
    }


    override fun getItemCount(): Int {
     return allCategory.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

    }
}