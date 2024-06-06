package com.example.kviz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kviz.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizModelList: MutableList<QuizModel> = mutableListOf()
    private lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromFirebase()
    }

    private fun setupRecyclerView() {
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getDataFromFirebase() {
        FirebaseDatabase.getInstance().reference
            .get() // Get the data
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        val quizModel = snapshot.getValue(QuizModel::class.java)
                        quizModel?.let {
                            quizModelList.add(it)
                        }
                    }
                    setupRecyclerView()
                }
            }
    }
}