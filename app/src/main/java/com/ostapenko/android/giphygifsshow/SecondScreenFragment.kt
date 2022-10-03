package com.ostapenko.android.giphygifsshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class SecondScreenFragment : Fragment() {

    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.second_screen_fragment, container, false)
        imageView = view.findViewById(R.id.second_screen_image_view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString("url")

        //Log.d("SecondScreen","url from bundle: $url")

        Glide.with(this).load(url).placeholder(R.drawable.lucy)
            .into(imageView)
    }
}