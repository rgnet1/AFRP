package com.example.afrp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.afrp.databinding.ActivityMainBinding
import com.example.afrp.fragments.ConventionsFragment
import com.example.afrp.fragments.NewsFragment
import com.example.afrp.fragments.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private val newsFragment = NewsFragment()
    private val conventionFragment = ConventionsFragment()
    private val notificationsFragment = NotificationsFragment()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default, start with newsFragment
        replaceFragment(conventionFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            val goToFragment = when(it.itemId){
                R.id.ic_conventions -> conventionFragment
                R.id.ic_news -> newsFragment
                else -> notificationsFragment
            }
            replaceFragment(goToFragment)
            true
        }


    }


    private fun replaceFragment(fragment: Fragment){
//        if(fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
//        }
    }
}