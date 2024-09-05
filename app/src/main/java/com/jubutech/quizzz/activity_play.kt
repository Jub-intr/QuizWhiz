package com.jubutech.quizzz

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jubutech.quizzz.databinding.ActivityMainBinding
import com.jubutech.quizzz.databinding.ActivityPlayBinding

class activity_play : AppCompatActivity() {

    lateinit var binding: ActivityPlayBinding

    val quizelist = listOf<quizes>(
        quizes("What is the term for the study of birds? ",
            "Anthology",
            "Prodigy",
            "Ornithology",
            "None",
            "Ornithology"),

        quizes("What is the loudest animal on Earth?",
            "Elephant",
            "Lion",
            "Sperm Whale",
            "None of the above",
            "Sperm Whale"),

        quizes("How many hearts does an octopus have?",
            "1",
            "2",
            "3",
            "None of the above",
            "3"),

        quizes("Which country has won the most World Cups?",
            "Argentina",
            "Germany",
            "Bangladesh",
            "Brazil",
            "Brazil"),


        quizes("Who was the highest-paid athlete in 2023?",
            "Messi",
            "Neymar",
            "Ronaldo",
            "None",
            "Ronaldo"),



        )

    var index = 0
    var updateQuestion = 1
    var hasFinished = false

    var correct = 0
    var wrong = 0
    var skip = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializequestion()


        binding.nextbtn.setOnClickListener{
            showQuestion()
        }

    }

    private fun initializequestion() {
        val quiz = quizelist[index]

        binding.apply {

            view1.text = quiz.question
            rd1.text = quiz. option01
            rd2.text = quiz.option02
            rd3.text = quiz.option03
            rd4.text = quiz.option04
        }


    }

    fun showQuestion() {
        checkAnswer()
        binding.apply {
            if (updateQuestion < quizelist.size) {
                updateQuestion++
                initializequestion()
            } else if (index <= quizelist.size - 1) {
                index++
            } else {
                hasFinished = true
            }
            rdgroup.clearCheck()
        }
    }

    private fun checkAnswer() {

        binding.apply {
            if (rdgroup.checkedRadioButtonId == -1) {
                skip++
            } else {
                val checkButton = findViewById<RadioButton>(rdgroup.checkedRadioButtonId)
                val checkAnswer = checkButton.text.toString()

                if (checkAnswer == quizelist[index].answer) {
                    correct++
                    showAlertDialogue("You are correct")
                } else {
                    wrong++
                    showAlertDialogue("You are wrong dear")
                }
            }
            if (index <= quizelist.size -1 ){

                index++
            }else{
                showAlertDialogue("Finished")
            }

        }

    }
    fun showAlertDialogue(message: String){

        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)

        builder.setPositiveButton("Ok", object: DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {


                if(message == "Finished"){
                    val intent = Intent(this@activity_play, activity_result::class.java)

                    intent.putExtra("correct", correct)
                    intent.putExtra("wrong", wrong)
                    intent.putExtra("skip", skip)

                    startActivity(intent)
                }

            }
        })

        val alertDialog = builder.create()
        alertDialog.show()

    }
}


