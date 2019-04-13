package com.noober.backgroudlibrary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.noober.background.BackgroundLibrary
import kotlinx.android.synthetic.main.activity_main.*

class KTActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ttt.setOnClickListener {
            Log.e("sss", "sdadssada")
        }
    }
}
