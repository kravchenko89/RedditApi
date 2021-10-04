package ru.kravchenko.testreddit

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import android.os.Environment
import java.io.File


class ListAdapterMain(val getModel: Result): RecyclerView.Adapter<CustomerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view, parent, false
        )
        return CustomerViewHolder(view)
    }

    override fun getItemCount() = getModel.data.children.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {

        val fullInfo = getModel.data.children[position]

        val date = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(fullInfo.data.created),
            ZoneId.systemDefault()
        ).toLocalTime()

        fun getPostedData(): String {

            val datanow = LocalDateTime.now().toLocalTime()
            val diff: Duration = Duration.between(datanow, date)
            return String.format(
                Locale.ENGLISH, "Posted : ${diff.toDays()} days ${diff.toHours()} hours ego"
            )
        }

        holder.title.text = "Title : ${fullInfo.data.title}"
        holder.fullName.text = "Author : ${fullInfo.data.author}"
        Picasso.get().load(fullInfo.data.thumbnail).into(holder.thumbnail)
        holder.comment.text = "Comment amount : ${fullInfo.data.num_comments}"
        holder.created.text = getPostedData()
        holder.cteatedData.text =
            Instant.ofEpochSecond(fullInfo.data.created).atZone(ZoneId.systemDefault())
                .toLocalDateTime().toString()


        holder.thumbnail.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(fullInfo.data.thumbnail))
            holder.thumbnail.context.startActivity(browserIntent)
            println(holder.thumbnail.context.startActivity(browserIntent))
        }


        holder.btnSaveImage.setOnClickListener {
            val url = fullInfo.data.thumbnail
            val context: Context = holder.thumbnail.context

            val request = DownloadManager.Request(Uri.parse(url))
                .setTitle("Title_test_image")
                .setDescription("Download")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + fullInfo.data.author + "_test_image" + ".jpg")

            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            dm.enqueue(request)
        }

    }
}
