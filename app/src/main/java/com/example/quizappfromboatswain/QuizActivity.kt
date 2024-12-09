package com.example.quizappfromboatswain

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizappfromboatswain.databinding.ActivityQuizBinding
import com.example.quizappfromboatswain.databinding.ScoreDialogBinding


class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var time : String = ""
    }

    lateinit var binding: ActivityQuizBinding
    var currentQuestionIndex = 0;
    var selectedAnswer = ""
    var score = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }

        loadQuestion()
        startTimer()
    }

    private fun startTimer(){
        val totalTimeInMills = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMills, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished/ 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicator.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }.start()
    }

    private fun loadQuestion(){
        selectedAnswer = ""
        if (currentQuestionIndex == questionModelList.size){
            finishQuiz()
            return
        }

        binding.apply {
            questionIndicator.text = "Question ${currentQuestionIndex + 1}/ ${questionModelList.size}"
            quizProgressIndicator.progress = (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            question.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]
        }
    }

    override fun onClick(v: View?) {

        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.grey))
            btn1.setBackgroundColor(getColor(R.color.grey))
            btn2.setBackgroundColor(getColor(R.color.grey))
            btn3.setBackgroundColor(getColor(R.color.grey))
        }

        val clickebtn = v as Button
        if (clickebtn.id == R.id.next_btn){
            if(selectedAnswer == questionModelList[currentQuestionIndex].correct){
                score++
                Log.i("Score of quiz", score.toString())
            }
            currentQuestionIndex++
            loadQuestion()
        }else{
            selectedAnswer= clickebtn.text.toString()
            clickebtn.setBackgroundColor(getColor(R.color.orange))
        }
    }

    private fun finishQuiz(){
        val totalQuestion = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestion.toFloat()) * 100).toInt()

        val DialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        DialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text= "$percentage %"
            if(percentage > 60){
                scoreTitle.text = "Congrats! You have passed. Good swimming!!!"
                scoreTitle.setTextColor(Color.BLUE)
            }else{
                scoreTitle.text = "Damn! BoatSwain are't happy -_-"
                scoreTitle.setTextColor(Color.RED)
            }
            scoreSubtitle.text = "$score out of $totalQuestion are correct"
            finishBtn.setOnClickListener{
                finish()
            }
        }

        AlertDialog.Builder(this)
            .setView(DialogBinding.root)
            .setCancelable(false)
            .show()
    }
}