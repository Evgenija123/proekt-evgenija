package com.example.kviz

import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import com.example.kviz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(),View.OnClickListener{
    companion object{
        var questionModelList:List<QuestionModel> = listOf()
        var time:String=""
    }

    lateinit var binding:ActivityQuizBinding
    var currentQuestionIndex=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }

        loadQuestions()
        startTimer()
    }
    private fun startTimer() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis, 1000L){

        override fun onTick(millisUntilFinished:Long){
            val seconds=millisUntilFinished/1000
            val minutes=seconds/60
            val remainingSeconds=seconds%60
            binding.timerIndicatorTextview.text=String.format("%02d:%02d",minutes,remainingSeconds)
    }
        override fun onFinish() {
            //finish the quiz

        }
        }
    }.start()
    private fun loadQuestions(){
binding.apply {
    questionIndicatorTextview.text="Question ${currentQuestionIndex+1}/ ${questionModelList.size}"
    questionProgressIndicator.progress=
        (currentQuestionIndex.toFloat()/ questionModelList.size.toFloat()*100).toInt()
    btn0.text= questionModelList[currentQuestionIndex].options[0]
    btn1.text= questionModelList[currentQuestionIndex].options[1]
    btn2.text= questionModelList[currentQuestionIndex].options[2]
    btn3.text= questionModelList[currentQuestionIndex].options[3]
}
    }
    override fun onClick(view:View?){

        val clickBtn=view as Button
        if(clickBtn.id==R.id.next_btn) {
            //next btn (is clicked)
            currentQuestionIndex++
            loadQuestions()
        }else{
            //opstions btn is clicked
        }
    }
}