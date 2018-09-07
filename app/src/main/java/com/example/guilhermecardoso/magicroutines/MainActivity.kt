package com.example.guilhermecardoso.magicroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.guilhermecardoso.magicroutines.search.SearchFragment
import android.R.menu
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = SearchFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }
}
