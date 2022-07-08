package com.byteroll.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.byteroll.myapplication.adapter.ScreenShotAdapter
import com.byteroll.myapplication.bean.ScreenShot
import com.byteroll.myapplication.databinding.ActivityMainBinding
import com.byteroll.myapplication.utils.UIUtil
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ScreenShotAdapter

    private val shots = mutableListOf(
        ScreenShot(R.drawable.card_1),
        ScreenShot(R.drawable.card_2),
        ScreenShot(R.drawable.card_3),
        ScreenShot(R.drawable.card_4),
        ScreenShot(R.drawable.card_5),
        ScreenShot(R.drawable.card_6),
        ScreenShot(R.drawable.card_7),
        ScreenShot(R.drawable.card_8),
        ScreenShot(R.drawable.card_9),
        ScreenShot(R.drawable.card_10)
    )

    private val shotList = ArrayList<ScreenShot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        UIUtil.setUIFlags(this)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.menu)
        }

        initShots()
        setRecyclerList()

        binding.navView.setCheckedItem(R.id.nav_done)
        binding.navView.setNavigationItemSelectedListener {
            binding.drawer.closeDrawers()
            true
        }
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT).setAction("Undo") {
                Toast.makeText(this, "Undo Delete", Toast.LENGTH_SHORT).show()
            }.show()
        }
        binding.refreshLayout.setOnRefreshListener {
            refreshScreenShots()
        }
    }

    private fun refreshScreenShots(){
        if (this::adapter.isInitialized){
            thread {
                Thread.sleep(2000)
                runOnUiThread {
                    initShots()
                    adapter.notifyDataSetChanged()
                    binding.refreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun initShots(){
        shotList.clear()
        repeat(100){
            val index = (0 until shots.size).random()
            shotList.add(shots[index])
        }
    }

    private fun setRecyclerList(){
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerList.layoutManager = layoutManager
        adapter = ScreenShotAdapter(this, shotList)
        binding.recyclerList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> "item add clicked".println()
            R.id.check -> "item check clicked".println()
            android.R.id.home -> binding.drawer.openDrawer(GravityCompat.START)
        }
        return true
    }

}

private fun <T> T?.println() = println(this)