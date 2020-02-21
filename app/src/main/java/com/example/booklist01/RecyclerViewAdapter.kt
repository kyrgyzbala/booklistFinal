package com.example.booklist01

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val mContext: Context,
                          private val items: List<Item>)
    :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val mImageView = itemView.findViewById<ImageView>(R.id.imageViewList)
        val mBookName = itemView.findViewById<TextView>(R.id.book_name_id)
        val mAuthor = itemView.findViewById<TextView>(R.id.book_author_id)
        val mRating = itemView.findViewById<TextView>(R.id.book_rating_id)
        val mPages = itemView.findViewById<TextView>(R.id.book_page_id)
        val mYear = itemView.findViewById<TextView>(R.id.book_year_id)
        val mLayout = itemView.findViewById<ConstraintLayout>(R.id.list_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = items[position]
        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.new_icon)
        /*Picasso.with(mContext).load(item.volumeInfo.imageLinks.thumbnail)
            .error(R.mipmap.new_icon).into(holder.mImageView)
        */

        Glide.with(mContext).load(item.volumeInfo.imageLinks.thumbnail).apply(options).into(holder.mImageView)


        holder.mBookName.text = item.volumeInfo.title
        holder.mAuthor.text = item.volumeInfo.title
        holder.mYear.text = item.volumeInfo.publishedDate
        holder.mPages.text = item.volumeInfo.pageCount.toString()
        holder.mRating.text = item.volumeInfo.averageRating.toString()

        holder.mLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.volumeInfo.previewLink)

            val chooser = Intent.createChooser(intent, "open with")
            mContext.startActivity(chooser)
        }
    }
}