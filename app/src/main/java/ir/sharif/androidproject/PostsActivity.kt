package ir.sharif.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.sharif.androidproject.webservice.WebserviceHelper
import ir.sharif.androidproject.webservice.webservices.posts.PostResponse
import kotlinx.android.synthetic.main.activity_posts.*
import kotlin.concurrent.thread

class PostsActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostAdapter
    private var isInGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        postList.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(arrayListOf())
        postList.adapter = postAdapter
        fetchPosts()
    }

    private fun fetchPosts() {
        thread(true) {
            val posts = WebserviceHelper.getPosts()
            runOnUiThread {
                postAdapter.replaceData(posts)
            }
        }
    }

    inner class PostAdapter(private var postList: ArrayList<PostResponse>) : RecyclerView.Adapter<PostViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PostViewHolder(LayoutInflater.from(this@PostsActivity).inflate(R.layout.post_item, null))

        override fun getItemCount() = postList.size

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.titleTextView).text = postList[position].title
            holder.itemView.findViewById<TextView>(R.id.bodyTextView).text = postList[position].body
        }

        fun replaceData(posts: List<PostResponse>) {
            postList.clear()
            postList.addAll(posts)
            notifyDataSetChanged()
        }
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
