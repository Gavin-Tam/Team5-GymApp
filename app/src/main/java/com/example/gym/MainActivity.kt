package com.suhaib.gymlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), Adapter.MyClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exercisesList: ArrayList<Exercises>
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        exercisesList = ArrayList()
        exercisesList.add(Exercises(R.drawable.shoulders,"Shoulder Exercise"))
        exercisesList.add(Exercises(R.drawable.arms,"Arms Exercise"))
        exercisesList.add(Exercises(R.drawable.chest,"Chest Exercise"))
        exercisesList.add(Exercises(R.drawable.abs,"Abs Exercise"))
        exercisesList.add(Exercises(R.drawable.legs,"Legs Exercise"))
        exercisesList.add(Exercises(R.drawable.back,"Back Exercise"))
        exercisesList.add(Exercises(R.drawable.butt,"Butt Exercise"))
        exercisesList.add(Exercises(R.drawable.thigh,"Thigh Exercise"))


        adapter = Adapter(exercisesList, this@MainActivity)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(position: Int) {
        when(position){
            0 -> startActivity(Intent(this,ShouldersExercise::class.java))
            1 -> startActivity(Intent(this,ArmsExercise::class.java))
            2 -> startActivity(Intent(this,ChestExercise::class.java))
            3 -> startActivity(Intent(this,AbsExercise::class.java))
            4 -> startActivity(Intent(this,LegsExercise::class.java))
            5 -> startActivity(Intent(this,BackExercise::class.java))
            6 -> startActivity(Intent(this,ButtExercise::class.java))
            7 -> startActivity(Intent(this,ThighExercise::class.java))



        }

    }
}