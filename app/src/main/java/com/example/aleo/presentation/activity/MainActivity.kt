package com.example.aleo.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aleo.R
import com.example.aleo.databinding.ActivityMainBinding
import com.example.aleo.presentation.education.fragments.EducationFragment
import com.example.aleo.presentation.explorer.ExplorerFragment
import com.example.aleo.presentation.explorer.ionbackpressed.IOnBackPressed
import com.example.aleo.presentation.faq.fragments.FaqFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentFragment: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startTransaction(ExplorerFragment.newInstance(), EXPLORER_INT)
        injectFragment()
    }

    companion object {
        const val EXPLORER_INT: Int = 1
        const val Education_INT: Int = 2
        const val FAQ_INT: Int = 3
    }

    private fun injectFragment() {
        binding.bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.explorerMenuItem -> {
                    startTransaction(ExplorerFragment.newInstance(), EXPLORER_INT)
                    true
                }
                R.id.educationMenuItem -> {
                    startTransaction(EducationFragment.newInstance(), Education_INT)
                    true
                }
                R.id.faqMenuItem -> {
                    startTransaction(FaqFragment.newInstance(), FAQ_INT)
                    true
                }
                else -> false
            }
        }
    }

    private fun startTransaction(fragment: Fragment, fragmentType: Int) {
        if (fragmentType != currentFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, fragment)
                .commit()
            currentFragment = fragmentType
        } else return
    }



    override fun onBackPressed() {
        (this.supportFragmentManager.fragments.last() as? IOnBackPressed?)?.onBackPressed()
            ?: super.onBackPressed()
    }
}



