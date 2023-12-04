package com.example.eusercraftzone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.eUserCraftzone.Model.AddProductModel
import com.example.eUserCraftzone.Model.categoryModel
import com.example.eusercraftzone.adapter.ProductAdapter
import com.example.eusercraftzone.adapter.categoryAdapter
import com.example.eusercraftzone.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        getcategories()
        getSliderImage()
        getProducts()
        return binding.root
    }

    private fun getSliderImage() {
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderimage)
            }
    }

    private fun getProducts() {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("Products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(AddProductModel::class.java)
                    if(data != null)
                    //list.add(data!!)
                        list.add(data)
                }
             binding.productRecycler.adapter = ProductAdapter(requireContext(), list)
            }
    }
    private fun getcategories() {
        val list = ArrayList<categoryModel>()
        Firebase.firestore.collection("category")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(categoryModel::class.java)
                   // list.add(data!!)
                    if(data != null)
                        list.add(data)
                }
                binding.categoryRecycler.adapter =categoryAdapter(requireContext(), list)

            }
    }
    }

