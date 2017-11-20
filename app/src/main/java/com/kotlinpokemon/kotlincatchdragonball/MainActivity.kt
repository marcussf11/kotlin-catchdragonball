package com.kotlinpokemon.kotlincatchdragonball

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var score:Int = 0
    var imgArray = ArrayList<ImageView>()
    var handler:Handler = Handler()
    var runnable:Runnable = Runnable{}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgArray = arrayListOf(db0, db1, db2, db3, db4, db5, db6, db7, db8)

        hideImages()

        db0.setOnClickListener { increaseScore() }
        db1.setOnClickListener { increaseScore() }
        db2.setOnClickListener { increaseScore() }
        db3.setOnClickListener { increaseScore() }
        db4.setOnClickListener { increaseScore() }
        db5.setOnClickListener { increaseScore() }
        db6.setOnClickListener { increaseScore() }
        db7.setOnClickListener { increaseScore() }
        db8.setOnClickListener { increaseScore() }

        object : CountDownTimer(10000, 1000){
            override fun onFinish() {
                tx_timer.text = "Time's OFF!!!"
                handler.removeCallbacks(runnable)
                for (image in imgArray){
                    image.visibility = View.INVISIBLE
                }
            }
            override fun onTick(millisUntilFinished: Long) {
                tx_timer.text = "Time: "+millisUntilFinished/1000
            }
        }.start()
    }

    private fun hideImages(){

        runnable = object : Runnable{
            override fun run() {
                for (image in imgArray){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                var index = random.nextInt(8 - 0)
                imgArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }

    private fun increaseScore(){
        score++
        tx_score.text = "Score: $score"
    }
}
