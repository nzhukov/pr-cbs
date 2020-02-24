package com.example.pr_cbs.AsynTasks

import android.content.Context
import android.os.AsyncTask
import com.example.pr_cbs.RecordStorage.RecommendedBookStorage

class LoadRecommendedBookAsyncTask(
    callback: RecommendedATFinished,
    var context: Context,
    var downloadLatestBooksFromDatabase: Boolean,
    private var onNoResultsFound: () -> Unit

) : AsyncTask<Unit, Unit, Unit>() {

    private val callback: RecommendedATFinished = callback


    private var hasResult: Boolean = true

    override fun onPreExecute() {
        super.onPreExecute()

        RecommendedBookStorage.Instance().clear()
    }

    override fun doInBackground(vararg p0: Unit?) {
        this.hasResult = RecommendedBookStorage.Instance()
            .downloadRecommendedBooks(downloadLatestBooksFromDatabase, context)

    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)

        if (!this.hasResult) {
            onNoResultsFound()
        } else {
            //TODO
        }

        if (callback != null)
            callback.fromSecondATtoThird()
    }

    interface RecommendedATFinished {
        fun fromSecondATtoThird()
    }
}