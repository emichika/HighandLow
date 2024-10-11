package com.emichika.highandlow

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val tag = "high and low"
    private var yourCard = 0
    private var droidCard = 0
    private var hitCount = 0
    private var loseCount = 0
    private var gameStart = false
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // highBtn の要素を取得
        val highBtn: Button = findViewById(R.id.highBtn)
        highBtn.setOnClickListener {
            if ((gameStart && !answered)){
                highAndLow('h')
            }
        }

        // lowBtn の要素を取得
        val lowBtn: Button = findViewById(R.id.lowBtn)
        lowBtn.setOnClickListener {
            if ((gameStart && !answered)){
                highAndLow('l')
            }
        }

        // nextBtn の要素を取得
        val nextBtn: Button = findViewById(R.id.nextBtn)
        nextBtn.setOnClickListener {
            if (gameStart) {
                drawCard()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hitCount = 0
        loseCount = 0
        // hitText の要素を取得
        val hitText: TextView = findViewById(R.id.hitText)
        hitText.text = getString(R.string.hit_text)
        // loseText の要素を取得
        val loseText: TextView = findViewById(R.id.loseText)
        loseText.text = getString(R.string.lose_text)
        gameStart = true
        drawCard()
    }

    private fun drawCard(){
        // yourCardImage の要素を取得
        val yourCardImage: ImageView = findViewById(R.id.yourCardImage)
        yourCardImage.setImageResource(R.drawable.z02)
        // droidCardImage の要素を取得
        val droidCardImage: ImageView = findViewById(R.id.droidCardImage)
        droidCardImage.setImageResource(R.drawable.z01)
        // IntRange.random() で乱数を生成
        yourCard = (1..13).random()
        Log.d(tag, "You:$yourCard")
        when (yourCard) {
            1 -> yourCardImage.setImageResource(R.drawable.d01)
            2 -> yourCardImage.setImageResource(R.drawable.d02)
            3 -> yourCardImage.setImageResource(R.drawable.d03)
            4 -> yourCardImage.setImageResource(R.drawable.d04)
            5 -> yourCardImage.setImageResource(R.drawable.d05)
            6 -> yourCardImage.setImageResource(R.drawable.d06)
            7 -> yourCardImage.setImageResource(R.drawable.d07)
            8 -> yourCardImage.setImageResource(R.drawable.d08)
            9 -> yourCardImage.setImageResource(R.drawable.d09)
            10 -> yourCardImage.setImageResource(R.drawable.d10)
            11 -> yourCardImage.setImageResource(R.drawable.d11)
            12 -> yourCardImage.setImageResource(R.drawable.d12)
            13 -> yourCardImage.setImageResource(R.drawable.d13)
        }
        droidCard = (1..13).random()
        Log.d(tag, "droid:$droidCard")
        answered = false
    }

    private fun highAndLow(answer:Char) {
        showDroidCard()
        answered = true
        val balance = droidCard - yourCard
        // hitText の要素を取得
        val hitText: TextView = findViewById(R.id.hitText)
        // loseText の要素を取得
        val loseText: TextView = findViewById(R.id.loseText)
        // resultText の要素を取得
        val resultText: TextView = findViewById(R.id.resultText)

        if (balance == 0) {
            // when even do nothing
        } else if ((balance > 0 && answer == 'h')) {
            hitCount++
            hitText.text = buildString {
                append(getString(R.string.hit_text))
                append(hitCount)
            }
        } else if ((balance < 0 && answer == 'l')) {
            hitCount++
            hitText.text = buildString {
                append(getString(R.string.hit_text))
                append(hitCount)
            }
        } else {
            loseCount++
            loseText.text = buildString {
                append(getString(R.string.lose_text))
                append(loseCount)
            }
        }
        if (hitCount == 5) {
            resultText.text = " あなたの勝ちです "
            gameStart = false
        } else if (loseCount == 5) {
            resultText.text = " あなたの負けです "
            gameStart = false
        } else {
            // do nothing
        }
    }

    private fun showDroidCard(){
        // droidCardImage の要素を取得
        val droidCardImage: ImageView = findViewById(R.id.droidCardImage)
        when (droidCard) {
            1 -> droidCardImage.setImageResource(R.drawable.c01)
            2 -> droidCardImage.setImageResource(R.drawable.c02)
            3 -> droidCardImage.setImageResource(R.drawable.c03)
            4 -> droidCardImage.setImageResource(R.drawable.c04)
            5 -> droidCardImage.setImageResource(R.drawable.c05)
            6 -> droidCardImage.setImageResource(R.drawable.c06)
            7 -> droidCardImage.setImageResource(R.drawable.c07)
            8 -> droidCardImage.setImageResource(R.drawable.c08)
            9 -> droidCardImage.setImageResource(R.drawable.c09)
            10 -> droidCardImage.setImageResource(R.drawable.c10)
            11 -> droidCardImage.setImageResource(R.drawable.c11)
            12 -> droidCardImage.setImageResource(R.drawable.c12)
            13 -> droidCardImage.setImageResource(R.drawable.c13)
        }
    }
}