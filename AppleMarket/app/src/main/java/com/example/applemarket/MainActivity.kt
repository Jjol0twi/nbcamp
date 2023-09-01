package com.example.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.adapter.MainProductListAdapter
import com.example.applemarket.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_INDEX = "position"
        const val EXTRA_DATA = "data"
    }

    private lateinit var binding: MainActivityBinding
    private val listAdapter by lazy { MainProductListAdapter() }
    private lateinit var detailActivityLauncher: ActivityResultLauncher<Intent>
    private val spinnerItems = arrayListOf<String>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        detailActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val itemIndex = it.data?.getIntExtra(ProductDetailActivity.EXTRA_INDEX, 0)
                    val isLike =
                        it.data?.getBooleanExtra(ProductDetailActivity.EXTRA_IS_LIKE, false)
                            ?: false
                    // TODo 이전과 같으면 처리 아니면 안 처리
                    if (itemIndex != null) {
                        if (isLike) {
                            listAdapter.addPostLike(itemIndex)
                        } else {
                            listAdapter.removePostLike(itemIndex)
                        }
                        listAdapter.notifyItemChanged(itemIndex)
                    }
                }
            }
    }

    private fun initView() = with(binding) {
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop: Boolean = true
        val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
        spinnerItems.add("내배캠동")
        val spinnerAdapter =
            ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, spinnerItems)
        productListView.adapter = listAdapter
        productListView.addItemDecoration(decoration)
        navListTop.visibility = View.INVISIBLE
        toolbar.setOnMenuItemClickListener { menuItemClickEvent(it) }
        toolbarSpinner.adapter = spinnerAdapter
        listAdapter.clickItemListener =
            object : MainProductListAdapter.OnClickItemListener {
                override fun itemProductClick(itemView: View, position: Int) {
                    Log.d("test", arrayOf(listAdapter.getUserLikePost())[0].toString())
                    val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
                    intent.putExtra(EXTRA_INDEX, position)
                    intent.putExtra(EXTRA_DATA, listAdapter.getProductListByIndex(position))
                    intent.putExtra("userPostLike", arrayOf(listAdapter.getUserLikePost()))
                    detailActivityLauncher.launch(intent)
                }
            }
        listAdapter.longClickItemListener =
            object : MainProductListAdapter.OnLongClickItemListener {
                override fun itemProductClick(itemView: View, position: Int) {
                    showDialog("상품  삭제", "상품을 삭제하시겠습니까?") {
                        listAdapter.removeProduct(position)
                        listAdapter.notifyItemRemoved(position)
                    }
                }
            }
        productListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!productListView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    navListTop.startAnimation(fadeOut)
                    navListTop.visibility = View.GONE
                    isTop = true
                } else {
                    if (isTop) {
                        navListTop.visibility = View.VISIBLE
                        navListTop.animation = fadeIn
                        isTop = false
                    }
                }
            }

        })
        navListTop.setOnClickListener {
            productListView.smoothScrollToPosition(0)
        }

    }

    private fun showDialog(title: String, message: String, okAction: () -> Unit) {
        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("확인") { _, _ ->
                okAction()
            }
            setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun menuItemClickEvent(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_notification -> {
                getNotification()
                true
            }

            else -> false
        }
    }

    override fun onBackPressed() {
        showDialog("종료", "종료하시겠습니까?") {
            super.onBackPressed()
            finish()
        }
    }

    private fun getNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "main_channel"
            val channelName = "notification_button"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                enableVibration(true)
            }
            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)

        } else {
            // 26 버전 이하
            builder = NotificationCompat.Builder(this)
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다.")
        }
        manager.notify(1, builder.build())
    }
}