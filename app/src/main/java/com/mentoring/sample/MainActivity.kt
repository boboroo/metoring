package com.mentoring.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.mentoring.sample.ui.DetailFragment
import com.mentoring.sample.ui.MainFragment
import com.mentoring.sample.ui.mvp.MainFragment2
import dagger.hilt.android.AndroidEntryPoint

/**
 *   앞으로 하게 될 것들
 *   - 추상화, 요구사항 추가, 패키지구조 변경
 *   - ViewModel, LiveData, DI 전부 써보기
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}