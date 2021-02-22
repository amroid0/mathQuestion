package com.amroid.mathquestion.data.model

import java.util.*

data class Operation (
                      val id:UUID,
                      val firstNumber:Int,
                      val secondNumber:Int,
                      val Operation: MathOp,
                      val state:TaskState


)