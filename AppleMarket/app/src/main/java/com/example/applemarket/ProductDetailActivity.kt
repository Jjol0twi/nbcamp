package com.example.applemarket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.adapter.MainProductListAdapter
import com.example.applemarket.databinding.ProductDetailActivityBinding
import com.example.applemarket.model.ProductItem
import kotlin.properties.Delegates

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ProductDetailActivityBinding
    private val dataList: ProductItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", ProductItem::class.java)
        } else {
            intent.getParcelableExtra<ProductItem>("data")
        }
    }
    private val dataPosition: Int by lazy { intent.getIntExtra("index", 0) }
    private val userPostLike by lazy { intent.getIntegerArrayListExtra("userPostLike") }
    private var isLike by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isLike = userPostLike?.contains(dataPosition)?: false
        initView()
        binding.backToMainButton.setOnClickListener {
            backToMain()
        }
    }

    private fun backToMain() {
        val intent = Intent().apply {
            putExtra("index", dataPosition)
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) {
            finish()
        }
    }

    private fun initView() = with(binding) {
        dataList?.image?.let { productImage.setImageResource(it) }
        productNameText.text = dataList?.name
        productDescText.text = dataList?.description
        userName.text = dataList?.seller
        userLocate.text = dataList?.address
        productPrice.text = getString(R.string.main_product_price_text, dataList?.price)
        Log.d("test", isLike.toString()+userPostLike.toString()+intent.getIntegerArrayListExtra("userPostLike").toString())
        if (isLike) {
            postLike.setImageResource(R.drawable.ic_favorite)
        } else {
            postLike.setImageResource(R.drawable.ic_favorite_border)
        }
        postLike.setOnClickListener {
            likeClickEvent()
        }
    }

    private fun likeClickEvent() {
        if (isLike) {
            binding.postLike.setImageResource(R.drawable.ic_favorite_border)
            isLike = !isLike
        } else {
            binding.postLike.setImageResource(R.drawable.ic_favorite)
            isLike = !isLike
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backToMain()
    }

}