package com.example.kviz

data class QuizModel(
    val id:String,
    val title:String,
    val subtitute:String,
    val time:String,
)
{
    constructor():this(" "," "," "," ")
}
