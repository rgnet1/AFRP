package com.example.afrp.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.example.afrp.FragmentCommunicator
import com.example.afrp.R
import com.example.afrp.model.NewsItems
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
class ItemAdapter(private val context: Context,
                  private val dataset: List<NewsItems>,
                  private val communicator: FragmentCommunicator): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
//    private lateinit var communicator: FragmentCommunicator

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.titleTextView)
        val desc: TextView = view.findViewById(R.id.decTextView)
        val img: ImageView = view.findViewById(R.id.image)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_row, parent, false)
//        this.communicator = communicator
        val vh = ItemViewHolder(adapterLayout)
        vh.itemView.setOnClickListener{
            val position = vh.adapterPosition
            if(position != NO_POSITION){
                Toast.makeText(context, "Position $position", Toast.LENGTH_SHORT).show()
                val url = dataset[position].url
                communicator.passData(url)
//                communicator.passData(url)
//                itemClick(url)

//                val weFragment: WebFragment
//
//                val webPageFragment =  WebFragment()
//                val transaction = supportFragmentManager.beginTransaction()
//                transaction.replace(R.id.fragment_container, webPageFragment)
//                transaction.commit()
            }
        }
        return vh
    }

    override fun getItemCount() = dataset.size

    // Binds data set to a row in the view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.title.text = item.mainTitle
        holder.desc.text = item.description
        val imageUrl: String = item.image

        if (imageUrl.isNotBlank()) {
            // Set image
            Picasso.get()
                .load(imageUrl)
                .into(holder.img, object : Callback {
                    override fun onSuccess() {
                        Log.d(TAG, "success")
                    }

                    override fun onError(e: Exception?) {
                        Log.d(TAG, "error")
                    }
                })
        }
//        holder.img.setImageResource(item.image)

    }


}