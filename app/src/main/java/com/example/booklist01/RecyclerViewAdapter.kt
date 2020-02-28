package com.example.booklist01

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(
    private val mContext: Context,
    private val items: List<Item>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView = itemView.findViewById<ImageView>(R.id.imageViewList)
        val mBookName = itemView.findViewById<TextView>(R.id.book_name_id)
        val mAuthor = itemView.findViewById<TextView>(R.id.book_author_id)
        val mRating = itemView.findViewById<TextView>(R.id.book_rating_id)
        val mPages = itemView.findViewById<TextView>(R.id.book_page_id)
        val mYear = itemView.findViewById<TextView>(R.id.book_year_id)
        val mLayout = itemView.findViewById<ConstraintLayout>(R.id.list_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_items,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = items[position]
        Log.d("HEHE", "onBind called")
        val imageLink: String = item.volumeInfo.imageLinks.thumbnail + ".jpg"

        Picasso.with(mContext).load(imageLink).placeholder(R.drawable.icon_google)
            .error(R.mipmap.new_icon).resize(121, 126).centerCrop().into(holder.mImageView)

        Log.d("HEHE", imageLink)

        holder.mBookName.text = item.volumeInfo.title
        holder.mAuthor.text = item.volumeInfo.authors[0]
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