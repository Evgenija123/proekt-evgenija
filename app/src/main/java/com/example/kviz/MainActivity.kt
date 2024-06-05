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


        quizModelList= mutableListOf()
        getDataFromFirebase()
    }
    private fun setupRecyclerView(){
adapter= QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter
    }

    private fun getDataFromFirebase(){
        val listQuestionModel= mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("What is android?", mutableListOf("Language","OS","Product","None"),"OS"))
        listQuestionModel.add(QuestionModel("Prasanje 2", mutableListOf("Language","OS","Product","None"),"OS"))
        listQuestionModel.add(QuestionModel("Prasanje 3", mutableListOf("Language","OS","Product","None"),"OS"))


        quizModelList.add(QuizModel( "1","Sport ","sport 1","10",listQuestionModel))
        quizModelList.add(QuizModel( "2","Sciene 2","science 2","20"))
        quizModelList.add(QuizModel( "3","Movies","movies 3","15"))

        setupRecyclerView()

    }
}