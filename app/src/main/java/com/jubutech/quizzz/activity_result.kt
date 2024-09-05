package com.jubutech.quizzz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jubutech.quizzz.databinding.ActivityResultBinding

class activity_result : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correct = intent.getIntExtra("correct", 0)
        val wrong = intent.getIntExtra("wrong", 0)
        val skip = intent.getIntExtra("skip", 0)

        binding.corr.text = "Correct : 0$correct"
        binding.wro.text = "Wrong : 0$wrong"
        binding.ski.text = "Skip : 0$skip"



    }
}