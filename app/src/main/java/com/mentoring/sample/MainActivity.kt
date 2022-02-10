package com.mentoring.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mentoring.sample.ui.ProductsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ProductsFragment.newInstance())
                .commit()
        }
    }
}