package ir.sharif.androidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.webservice.webservices.comments.CommentResponse
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : AppCompatActivity(), Advertiser.AdvertiseListener<List<CommentResponse>> {
    private lateinit var commentAdapter: CommentAdapter
    private var postId: Int = 1
    private var isSubscribed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        Advertiser.subscribe(this, AdvertisementType.COMMENTS_LOADED)
        isSubscribed = true
        setSupportActionBar(commentsToolbar)
        postId = intent.getIntExtra("postId", 1)
        title = "comments"
        commentList.layoutManager = LinearLayoutManager(this)
        commentAdapter = CommentAdapter(arrayListOf())
        commentList.adapter = commentAdapter
        fetchComments(postId)
    }

    override fun onResume() {
        super.onResume()
        if (!isSubscribed) {
            Advertiser.subscribe(this, AdvertisementType.COMMENTS_LOADED)
        }
    }

    override fun onPause() {
        super.onPause()
        Advertiser.unSubscribe(this, AdvertisementType.COMMENTS_LOADED)
        isSubscribed = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.comments_menu, menu)
        return true
    }

    private fun fetchComments(postId: Int) = MessageController.fetchComments(postId)

    override fun receiveData(advertisement: Advertisement<List<CommentResponse>>) {
        runOnUiThread {
            commentAdapter.replaceData(advertisement.data)
            val commentsSize = advertisement.data.size
            title = "Post $postId,$commentsSize comments"
        }
    }

    inner class CommentAdapter(private var commentList: ArrayList<CommentResponse>) :
        RecyclerView.Adapter<CommentViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CommentViewHolder(LayoutInflater.from(this@CommentsActivity).inflate(R.layout.comment_item, null))

        override fun getItemCount() = commentList.size

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.nameTextView).text = commentList[position].name
            holder.itemView.findViewById<TextView>(R.id.emailTextView).text = commentList[position].email
            holder.itemView.findViewById<TextView>(R.id.bodyTextView).text = commentList[position].body
        }

        fun replaceData(comments: List<CommentResponse>) {
            commentList.clear()
            commentList.addAll(comments)
            notifyDataSetChanged()
        }
    }

    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
