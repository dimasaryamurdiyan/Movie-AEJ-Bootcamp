package com.singaludra.moviebootcamp.presentation.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.singaludra.moviebootcamp.utils.Utils

abstract class BaseActivity : AppCompatActivity()  {
    protected var progressDialog: ProgressDialog? = null

    fun hideLoading() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    fun showLoading() {
        hideLoading()
        progressDialog = Utils.showLoadingDialog(this)
    }
}