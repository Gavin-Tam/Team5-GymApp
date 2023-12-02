package com.suhaib.gymlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val ExercisesList: ArrayList<Exercises>, val listener:MyClickListener):RecyclerView.Adapter<Adapter.MyViewHolder> (){

    inner class MyViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val texView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener{
                val position = adapterPosition
                listener.onClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val exercises = ExercisesList[position]
        holder.texView.text = exercises.name
        holder.imageView.setImageResource(exercises.image)
    }

    override fun getItemCount(): Int {
        return ExercisesList.size
    }

    interface MyClickListener{

        fun onClick(position: Int)

    }


}