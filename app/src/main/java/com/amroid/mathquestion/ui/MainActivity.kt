package com.amroid.mathquestion.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amroid.mathquestion.R
import com.amroid.mathquestion.databinding.ActivityMainBinding
import com.amroid.mathquestion.services.MathWorker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: OperationsAdapter
    private lateinit var mathViewModel: BlurViewModel
    private lateinit var binding:ActivityMainBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=setContentView(this,R.layout.activity_main)
        mathViewModel= ViewModelProviders.of(this).get(BlurViewModel::class.java)

     binding.fab.setOnClickListener {
            val num= editTextTextPersonName.text.toString().toInt()
            val delay= editTextPhone.text.toString().toLong()
            mathViewModel.applyCalc(num,delay)

        }
          setupRecycle()

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

    private fun setupRecycle(){
        adapter=OperationsAdapter();
        binding.operationList.adapter=adapter

    }
}