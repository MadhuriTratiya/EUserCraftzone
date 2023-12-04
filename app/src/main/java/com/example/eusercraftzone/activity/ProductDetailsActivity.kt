package com.example.eusercraftzone.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.eusercraftzone.databinding.ActivityProductDetailsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var bindining : ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindining = ActivityProductDetailsBinding.inflate(layoutInflater)

        getProductDetails(intent.getStringExtra("id"))
        setContentView(bindining.root)




    }

    private fun getProductDetails(prodId: String?) {

        Firebase.firestore.collection("products")
            .document(prodId!!).get().addOnSuccessListener {
                val list = it.get("productsImage") as ArrayList<String>
                bindining.textView6.text =it.getString("productName")
                bindining.textView7.text =it.getString("productSp")
                bindining.textView8.text =it.getString("productDescription")

                val slideList = ArrayList<SlideModel>()
                for(data  in list){
                    slideList.add(SlideModel(data, ScaleTypes.FIT))
                }
            }.addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_LONG).show()
            }
    }
}