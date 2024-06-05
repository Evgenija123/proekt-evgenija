package com.example.kviz

data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val time: String
) {
    constructor() : this(" ", " ", " ", " ")
}

data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correct: String
) {
    constructor() : this(" ", emptyList(), " ")
}
