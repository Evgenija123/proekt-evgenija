package com.example.kviz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kviz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var quizModelList: MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        QuizModel= mutableListOf()
        getDataFromFirebase()
    }
    private fun setupRecyclerView(){
adapter= QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter
    }

    private fun getDataFromFirebase(){
        quizModelList.add(QuizModel( "1","Programming 1","Basic programming 1","10"))
        quizModelList.add(QuizModel( "2","Programming 2","Basic programming 2","20"))
        quizModelList.add(QuizModel( "3","Programming 3","Basic programming 3","15"))
        setupRecyclerView()

    }
}