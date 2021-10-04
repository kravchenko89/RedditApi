package ru.kravchenko.testreddit

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomerViewHolder( view: View): RecyclerView.ViewHolder(view) {
    val fullName = view.findViewById<TextView>(R.id.author_name)
    val comment: TextView = view.findViewById(R.id.num_comments)
    val thumbnail: ImageView = view.findViewById(R.id.user_thumbnail)
    val created: TextView = view.findViewById(R.id.created_post)
    val title: TextView = view.findViewById(R.id.post_title)
    val cteatedData: TextView = view.findViewById(R.id.created_post_data)
    val btnSaveImage: Button = view.findViewById(R.id.btn_save_image)

}