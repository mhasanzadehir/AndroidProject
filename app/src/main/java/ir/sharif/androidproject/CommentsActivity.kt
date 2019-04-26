package ir.sharif.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.sharif.androidproject.webservice.WebserviceHelper
import ir.sharif.androidproject.webservice.webservices.comments.CommentResponse
import kotlinx.android.synthetic.main.activity_comments.*
import kotlin.concurrent.thread

class CommentsActivity : AppCompatActivity() {

    private lateinit var commentAdapter: CommentAdapter
    private var isInGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        title = "prj2"
        commentList.layoutManager = LinearLayoutManager(this)
        commentAdapter = CommentAdapter(arrayListOf())
        commentList.adapter = commentAdapter
        fetchcomments()

    }

    private fun fetchcomments() {
        thread(true) {
            val comments = WebserviceHelper.getComments(-1/*todo fill*/)
            runOnUiThread {
                commentAdapter.replaceData(comments)
            }
        }
    }

    inner class CommentAdapter(private var commentList: ArrayList<CommentResponse>) : RecyclerView.Adapter<CommentViewHolder>() {
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
