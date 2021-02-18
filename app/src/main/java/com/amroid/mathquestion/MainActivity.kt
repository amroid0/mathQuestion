package com.amroid.mathquestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mathViewModel: BlurViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mathViewModel= ViewModelProviders.of(this).get(BlurViewModel::class.java)

        button.setOnClickListener {
            val num= editTextTextPersonName.text.toString().toInt()
            val delay= editTextPhone.text.toString().toLong()
            mathViewModel.applyCalc(num,delay)

        }
        mathViewModel.outputWorkInfos.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = it[it.size-1]

            if (workInfo.state.isFinished) {



                // Normally this processing, which is not directly related to drawing views on
                // screen would be in the ViewModel. For simplicity we are keeping it here.
                val outputImageUri = workInfo.outputData.getInt(MathWorker.KEY_RES_NUM,0)
                Toast.makeText(this, outputImageUri.toString(), Toast.LENGTH_SHORT).show()
              mathViewModel.clear()
            }


        })


    }
}