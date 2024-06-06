package com.example.kviz

import android.content.IntentSender.OnFinished
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import com.example.kviz.databinding.ActivityQuizBinding
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.kviz.databinding.ScoreDialogBinding

import kotlin.math.log

class QuizActivity : AppCompatActivity(),View.OnClickListener{
    companion object{
        var questionModelList:List<QuestionModel> = listOf()
        var time:String=""
    }

    lateinit var binding:ActivityQuizBinding
    var currentQuestionIndex=0;
    var selectedAnswer=""
    var score=0;




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
        }.start()
    }
    private fun loadQuestions(){
selectedAnswer=""
if(currentQuestionIndex == questionModelList.size) {
    finishQuiz()   // koga ke sme na poslednoto prasanje ke odi na finishquiz
    return
}

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
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray)) //koga ke se selktira nekoe kopce ke bidi sino, ostanatite ke bidat sivi
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }

        val clickBtn=view as Button
        if(clickBtn.id==R.id.next_btn) {
            //next btn (is clicked)
            if (selectedAnswer== questionModelList[currentQuestionIndex].correct){
                score++

                Log.i("Score of quiz", score.toString())

            }
            currentQuestionIndex++
            loadQuestions()
        }else{
            //opstions btn is clicked
            selectedAnswer=clickBtn.text.toString()
            clickBtn.setBackgroundColor(getColor(R.color.blue))//bojata na kopcinjata koga se kliknati

        }

    }
    private fun finishQuiz(){
     val totalQuestions= questionModelList.size
        val percentage=((score.toFloat()/totalQuestions.toFloat())*100).toInt()

        val dialogBinding=ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress=percentage
            scoreProgressText.text = "$percentage%"
            if (percentage>60){

                scoreTitle.text="Congrats! You have passed"
                scoreTitle.setTextColor(Color.BLUE)
                scoreImage.setImageResource(R.drawable.trophy)

            } else{
                scoreTitle.text="Oops! You have failed"
                scoreTitle.setTextColor(Color.RED)
                scoreImage.visibility = View.GONE
            }
            scoreSubtitle.text="$score out of $totalQuestions are correct"
            finishBtn.setOnClickListener{
                finish()
            }

        }

        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()


    }
}