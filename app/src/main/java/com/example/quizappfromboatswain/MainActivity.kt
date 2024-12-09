package com.example.quizappfromboatswain

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizappfromboatswain.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var quizModelList: MutableList<QuiaModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()


    }

    private fun setupRecycleView(){
        adapter = QuizListAdapter(quizModelList)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
    }

    private fun getDataFromFirebase(){
        val listQuestionMidel = mutableListOf<QuestionModel>()
        listQuestionMidel.add(QuestionModel("What is android?", mutableListOf("OS", "Language", "Product", "None"), "OS"))
        listQuestionMidel.add(QuestionModel("Who owns android?", mutableListOf("Apple", "Google", "Samsung", "Microsoft"), "Google"))
        listQuestionMidel.add(QuestionModel("What assistant android uses?", mutableListOf("Siri", "Cortana", "Google assistant", "Alexa"), "Google assistant"))

        val listQuestionGeography = mutableListOf<QuestionModel>()
        listQuestionGeography.add(QuestionModel("What is the capital of France?", mutableListOf("Berlin", "Madrid", "Paris", "Rome"), "Paris"))
        listQuestionGeography.add(QuestionModel("Which river is the longest in the world?", mutableListOf("Amazon", "Nile", "Yangtze", "Mississippi"), "Nile"))
        listQuestionGeography.add(QuestionModel("What is the largest desert in the world?", mutableListOf("Sahara", "Arabian", "Gobi", "Kalahari"), "Sahara"))

        val listQuestionKotlin = mutableListOf<QuestionModel>()
        listQuestionKotlin.add(QuestionModel("What type of language is Kotlin?", mutableListOf("Compiled", "Interpreted", "Scripting", "Markup"), "Compiled"))
        listQuestionKotlin.add(QuestionModel("Which company developed Kotlin?", mutableListOf("JetBrains", "Google", "Microsoft", "Apple"), "JetBrains"))
        listQuestionKotlin.add(QuestionModel("What is a primary constructor in Kotlin?", mutableListOf("A function", "A property", "A class parameter", "An interface"), "A class parameter"))

        val listQuestionPython = mutableListOf<QuestionModel>()
        listQuestionPython.add(QuestionModel("What is Python primarily used for?", mutableListOf("Web Development", "Data Science", "Game Development", "All of the above"), "All of the above"))
        listQuestionPython.add(QuestionModel("Who created Python?", mutableListOf("Guido van Rossum", "James Gosling", "Bjarne Stroustrup", "Dennis Ritchie"), "Guido van Rossum"))
        listQuestionPython.add(QuestionModel("Which of the following is a Python web framework?", mutableListOf("Django", "Flask", "Ruby on Rails", "Both A and B"), "Both A and B"))

        quizModelList.add(QuiaModel("1", "Android" , "All basic about android", "5", listQuestionMidel))
        quizModelList.add(QuiaModel("2", "Geography", "All basics about geography", "5", listQuestionGeography))
        quizModelList.add(QuiaModel("3", "Kotlin", "All basics about Kotlin programming language", "5", listQuestionKotlin))
        quizModelList.add(QuiaModel("4", "Python", "All basics about Python programming language", "5", listQuestionPython))

        setupRecycleView()


    }
}