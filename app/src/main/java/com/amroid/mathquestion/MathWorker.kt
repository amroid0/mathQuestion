/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amroid.mathquestion;

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MathWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext

        val firstnum = inputData.getInt(KEY_FIRST_NUM,0)
        val secondnum = inputData.getInt(KEY_SECOND_NUM,0)
        return try {
           val result=firstnum+secondnum;

            Result.success(createOutputData(result))
        } catch (throwable: Throwable) {
            Log.d("WorkERRor", "doWork: ")
            Result.failure()
        }
    }
companion object{
    val TAG: String="MATH_WORKER"
    val KEY_FIRST_NUM="firstnum_key"
    val KEY_SECOND_NUM="second_key"
    val KEY_RES_NUM="res_key"
}
    private fun createOutputData(result:Int): Data {
        return Data.Builder().putInt(KEY_RES_NUM, result).build()
    }
}
