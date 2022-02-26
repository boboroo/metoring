package com.mentoring.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.mentoring.sample.databinding.ActivityMainBinding
import com.mentoring.sample.ui.ProductsFragment
import com.mentoring.sample.ui.RecentlyProductsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            val mainFragment = ProductsFragment.newInstance(binding.fullScreenFragmentContainer.id)

            mainFragment.lifecycle.addObserver(object: DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) {
                    binding.fullScreenFragmentContainer.visibility = View.VISIBLE
                }
            })

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, mainFragment)
                .commit()
        }

        binding.apply {
            btnRecent.setOnClickListener {
                showLocalNavigation(RecentlyProductsFragment.newInstance())
            }

            localNavigationOutSide.setOnClickListener {
                removeLocalNavigationFragment();
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()

        supportFragmentManager.popBackStack();
    }


    private fun showLocalNavigation(fragment: Fragment) {
        if (!isFinishing()) {
            fragment.lifecycle.addObserver(object: DefaultLifecycleObserver {
                override fun onStart(owner: LifecycleOwner) {
                    binding.localNavigationOutSide.visibility = View.VISIBLE
                }

                override fun onStop(owner: LifecycleOwner) {
                    binding.localNavigationOutSide.visibility = View.GONE
                }
            })

            supportFragmentManager.beginTransaction()
                .add(R.id.local_navigation_container, fragment)
                .addToBackStack(fragment.tag)
                .setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_right)
                .commitAllowingStateLoss()
        }
    }


    private fun removeLocalNavigationFragment() {
        if (binding.localNavigationContainer.size > 0) {
            supportFragmentManager.beginTransaction()
                .remove(binding.localNavigationContainer.getFragment())
                .commitAllowingStateLoss()
        }
    }
}