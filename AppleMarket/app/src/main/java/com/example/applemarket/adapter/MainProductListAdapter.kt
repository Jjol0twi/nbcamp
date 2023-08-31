package com.example.applemarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.R
import com.example.applemarket.databinding.MainProductItemBinding
import com.example.applemarket.model.ProductItem
import com.example.applemarket.model.UserData
import java.text.DecimalFormat

class MainProductListAdapter : RecyclerView.Adapter<MainProductListAdapter.ViewHolder>() {

    interface OnClickItemListener {
        fun itemProductClick(itemView: View, position: Int)
    }

    interface OnLongClickItemListener {
        fun itemProductClick(itemView: View, position: Int)

    }

    var clickItemListener: OnClickItemListener? = null
    var longClickItemListener: OnLongClickItemListener? = null
    private val productList = mutableListOf<ProductItem>()
    private val userDataList = mutableListOf<UserData>()

    fun getProductListByIndex(position: Int): ProductItem {
        return productList[position]
    }

    init {
        addItem(
            R.drawable.sample1,
            "산진 한달된 선풍기 팝니다",
            "이사가서 필요가 없어졌어요 급하게 내놓습니다",
            "대현동",
            1000,
            "서울 서대문구 창천동",
            13,
            25,

            )
        addItem(
            R.drawable.sample2, "김치냉장고", "이사로인해 내놔요", "안마담", 20000, "인천 계양구 귤현동", 8, 28,
        )
        addItem(
            R.drawable.sample3,
            "샤넬 카드지갑",
            "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
            "코코유",
            10000,
            "수성구 범어동",
            23,
            5,

            )
        addItem(
            R.drawable.sample4,
            "금고",
            "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다",
            "Nicole",
            10000,
            "해운대구 우제2동",
            14,
            17,

            )
        addItem(
            R.drawable.sample5,
            "갤럭시Z플립3 팝니다",
            "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
            "절명",
            150000,
            "연제구 연산제8동",
            22,
            9,
        )
        addItem(
            R.drawable.sample6,
            "프라다 복조리백",
            "까임 오염없고 상태 깨끗합니다\n정품여부모름",
            "미니멀하게",
            50000,
            "수원시 영통구 원천동",
            25,
            16,

            )
        addItem(
            R.drawable.sample7,
            "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
            "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",
            "굿리치",
            150000,
            "남구 옥동",
            142,
            54,

            )
        addItem(
            R.drawable.sample8,
            "샤넬 탑핸들 가방",
            "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n" +
                    "\n"
                    +
                    "색상 : 블랙\n" +
                    "사이즈 : 25.5cm * 17.5cm * 8cm\n"
                    +
                    "구성 : 본품더스트\n"
                    +
                    "\n"
                    +
                    "급하게 돈이 필요해서 팝니다 ㅠ ㅠ",
            "난쉽",
            180000,
            "동래구 온천제2동",
            31,
            7,

            )
        addItem(
            R.drawable.sample9,
            "4행정 엔진분무기 판매합니다.",
            "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n",
            "알뜰한",
            30000,
            "원주시 명륜2동",
            7,
            28,

            )
        addItem(
            R.drawable.sample10,
            "셀린느 버킷 가방",
            "22년 신세계 대전 구매입니당\n" +
                    "셀린느 버킷백\n"
                    +
                    "구매해서 몇번사용했어요\n"
                    +
                    "까짐 스크래치 없습니다.\n"
                    +
                    "타지역에서 보내는거라 택배로 진행합니당!",
            "똑태현",
            190000,
            "중구 동화동",
            40,
            6,
        )
    }

    init {
        addUser("admin",1)
    }

    private fun addUser(
        name: String,
        vararg likePost: Int
    ) {
        userDataList.add(
            UserData(
                name,
                likePost.toMutableList()
            )
        )
    }

    fun addPostLike(position: Int, name: String = "admin") {
        val index = getUserIndexByName(name)
        if (index != null) {
            userDataList[index].postLike.add(position)
            productList[position] =
                productList[position].copy(likeCount = formatNumber(productList[position].likeCount.toInt() + 1))
        }
    }

    fun getUserLikePost(name: String = "admin"): MutableList<Int>? {
        val index = getUserIndexByName(name)
        if (index != null) {
            return userDataList[index].postLike
        }
        return null
    }

    private fun getUserIndexByName(name: String): Int? {
        for ((index, userData) in userDataList.withIndex()) {
            if (userData.name == name) {
                return index
            }
        }
        return null
    }

    fun removeProduct(position: Int, name: String = "admin") {
        val index = getUserIndexByName(name)
        productList.removeAt(position)
        if (getUserLikePost()!=null&&index != null) {
            for (i in getUserLikePost()!!){
                if (i>position){
                    userDataList[index].postLike.remove(i)
                    userDataList[index].postLike.add(i-1)
                }
            }
        }
    }

    fun removePostLike(position: Int, name: String = "admin") {
        val index = getUserIndexByName(name)
        if (index != null) {
            userDataList[index].postLike.remove(position)
            productList[position] =
                productList[position].copy(likeCount = formatNumber(productList[position].likeCount.toInt() - 1))
        }
    }

    private fun addItem(
        image: Int,
        name: String,
        description: String,
        seller: String,
        price: Int,
        address: String,
        likeCount: Int = 0,
        chatCount: Int = 0,
    ) {
        productList.add(
            ProductItem(
                image,
                name,
                description,
                seller,
                formatNumber(price),
                address,
                likeCount.toString(),
                chatCount.toString()
            )
        )
    }

    private fun formatNumber(number: Int): String {
        val formatter = DecimalFormat("#,###")
        return formatter.format(number)
    }

    inner class ViewHolder(private val binding: MainProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemImage = binding.itemImg
        val itemName = binding.nameText
        val itemDesc = binding.productLocateText
        val itemPrice = binding.productPriceText
        val itemChatCount = binding.chatCountText
        val itemLikeCount = binding.likeCountText
        val itemLike = binding.likeImg
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MainProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        holder.itemView.setOnClickListener {
            clickItemListener?.itemProductClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            longClickItemListener?.itemProductClick(it, position)
            return@setOnLongClickListener true
        }
        itemImage.setImageResource(productList[position].image)
        itemName.text = productList[position].name
        itemDesc.text = productList[position].address
        itemPrice.text = itemPrice.context.getString(
            R.string.main_product_price_text,
            productList[position].price
        )
        itemChatCount.text = productList[position].chatCount
        itemLikeCount.text = productList[position].likeCount
        if (getUserLikePost()?.contains(position) == true) {
            itemLike.setImageResource(R.drawable.ic_favorite)
        }
    }

    override fun getItemCount(): Int = productList.size

}
