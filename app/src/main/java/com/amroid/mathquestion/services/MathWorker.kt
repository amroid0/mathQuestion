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

package com.amroid.mathquestion.services;

import android.content.Context
import android.util.Log
import androidx.work.*
import com.amroid.mathquestion.data.model.MathOp
import kotlinx.coroutines.delay

class MathWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {

        val firstnum = inputData.getInt(KEY_FIRST_NUM, 0)
        val secondnum = inputData.getInt(KEY_SECOND_NUM, 0)
        val responseTime = inputData.getInt(KEY_RESPONSE_TIME, 0)
        val operand = inputData.getInt(KEY_OPERAND, 0)
        return try {

            val result = when (operand) {
                MathOp.ADD.ordinal -> firstnum + secondnum
                MathOp.SUB.ordinal -> firstnum - secondnum
                MathOp.MULTI.ordinal -> firstnum * secondnum
                MathOp.DIVIDE.ordinal -> {
                    if (secondnum!=0)
                    firstnum / secondnum
                    else 0

                }
                else ->0

            }
            delay(responseTime * 1000L)
            Result.success(createOutputData(result))
        } catch (throwable: Throwable) {
            Log.d("WorkERRor", "doWork: ")
            Result.failure()
        }
    }

    companion object {
        const val TAG: String = "MATH_WORKER"
        const val KEY_FIRST_NUM = "firstnum_key"
        const val KEY_SECOND_NUM = "second_key"
        const val KEY_RES_NUM = "res_key"
        const val KEY_RESPONSE_TIME = "key_reponse_time"
        const val KEY_OPERAND = "key_operand"

    }

    private fun createOutputData(result: Int): Data {
        return Data.Builder().putInt(KEY_RES_NUM, result).build()
    }
}
