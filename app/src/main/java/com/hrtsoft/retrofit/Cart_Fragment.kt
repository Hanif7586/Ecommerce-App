package com.hrtsoft.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Cart_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart_, container, false)




        // Receive data from DetailsActivity
        val title = arguments?.getString("title")
        val price = arguments?.getString("price")
        val category = arguments?.getString("category")
        val description = arguments?.getString("description")
        val thumbnail = arguments?.getString("thumbnail")

        return view




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
