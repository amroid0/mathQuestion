package com.amroid.mathquestion.ui
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.amroid.mathquestion.services.MathWorker
import java.util.concurrent.TimeUnit

class BlurViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
    internal val outputWorkInfos: LiveData<List<WorkInfo>>

    init {
        // This transformation makes sure that whenever the current work Id changes the WorkInfo
        // the UI is listening to changes
        outputWorkInfos = workManager.getWorkInfosForUniqueWorkLiveData(MathWorker.TAG)
    }

    internal fun cancelWork() {
        workManager.cancelUniqueWork(MathWorker.TAG)
    }
    internal fun clear() {
        workManager.pruneWork();
    }


    internal fun applyCalc(num: Int, delay: Long) {
        val mathBuilder = OneTimeWorkRequestBuilder<MathWorker>().setInputData(buildInputDataForCalc(num,num)).setInitialDelay(delay,TimeUnit.SECONDS)

       workManager.enqueueUniqueWork(MathWorker.TAG,
            ExistingWorkPolicy.APPEND,
            mathBuilder.build());

    }
    private fun buildInputDataForCalc(num1:Int,num2:Int): Data {
        val builder = Data.Builder()
            builder.putInt(MathWorker.KEY_FIRST_NUM, num1)
           builder.putInt(MathWorker.KEY_SECOND_NUM, num2)

        return builder.build()
    }
}
