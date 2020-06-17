package com.example.flowers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ListFlowersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flower_list)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.textView, FlowerListFragment.newInstance(), "flowerList")
                .commit()
        }
    }
}