package com.ostapenko.android.giphygifsshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class FirstScreenFragment : Fragment() {

    private lateinit var giphyViewModel: GiphyViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var giphyAdapter: GiphyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        giphyViewModel = ViewModelProviders.of(this)[GiphyViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.first_screen_fragment, container, false)
        recyclerView = view.findViewById(R.id.first_screen_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        giphyViewModel.gifItemLiveData.observe(viewLifecycleOwner, Observer { gifItems ->
            giphyAdapter = GiphyAdapter(gifItems)
            recyclerView.adapter = giphyAdapter
        })

    }

    private class GiphyHolder(private val imageView: ImageView) :
        RecyclerView.ViewHolder(imageView) {

        fun bindGifsItem(gifsItem: GifsItem) {

            Glide.with(this.imageView.context).load(gifsItem.url).placeholder(R.drawable.lucy)
                .into(imageView)
        }
    }

    private inner class GiphyAdapter(private val gifItems: List<GifsItem>) :
        RecyclerView.Adapter<GiphyHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyHolder {

            val view = layoutInflater.inflate(R.layout.list_item_giphy, parent, false) as ImageView
            return GiphyHolder(view)
        }

        override fun getItemCount(): Int {
            return gifItems.size
        }

        override fun onBindViewHolder(holder: GiphyHolder, position: Int) {
            val gifItem = gifItems[position]

            holder.itemView.setOnClickListener {

                // Toast.makeText(requireContext(), "View Clicked", Toast.LENGTH_SHORT).show()
                val fragment = SecondScreenFragment()
                val urlGif = gifItem.url
                val bundle = Bundle()
                bundle.putString("url", urlGif)
                fragment.arguments = bundle

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_activity, fragment)
                    ?.addToBackStack(null)
                    ?.commit()

            }

            holder.bindGifsItem(gifItem)
        }
    }

    companion object {
        fun newInstance() = FirstScreenFragment()
    }
}


