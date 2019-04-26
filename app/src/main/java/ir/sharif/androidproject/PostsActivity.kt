package ir.sharif.androidproject

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.webservice.webservices.posts.PostResponse
import kotlinx.android.synthetic.main.activity_posts.*
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import java.util.logging.Logger


class PostsActivity : AppCompatActivity(), Advertiser.AdvertiseListener<List<PostResponse>> {
    private lateinit var postAdapter: PostAdapter
    private var isInGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        setSupportActionBar(postsToolbar)
        title = "Post Page"
        postList.layoutManager = LinearLayoutManager(this)
//        postList.margin
        postAdapter = PostAdapter(arrayListOf())
        postList.adapter = postAdapter
        tryToConnect()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.posts_menu, menu)
        menu?.findItem(R.id.gridSwitch)?.actionView?.findViewById<Switch>(R.id.switcher)?.setOnClickListener {
            runOnUiThread {
                if (isInGridMode) {
                    postList.layoutManager = LinearLayoutManager(this)
                    isInGridMode = false
                } else {
                    postList.layoutManager = GridLayoutManager(this, 3)
                    isInGridMode = true
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val dialogBuilder = AlertDialog.Builder(this)

        when(item?.itemId){
            R.id.Zeiny -> dialogBuilder.setMessage("Reza Zeiny 95 CE Sharif University of Technology.")
            R.id.Fatemi -> dialogBuilder.setMessage("Ali Reza Fatemi 95 CE Sharif University of Technology.")
            R.id.Hassanzadeh -> dialogBuilder.setMessage("Mahdi Hassanzade 95 CE Sharif University of Technology.")
            R.id.Mahdipour -> dialogBuilder.setMessage("Amir Hossein Mahdipour 95 CE Sharif University of Technology.")
            else -> return super.onOptionsItemSelected(item)
        }
        dialogBuilder.setCancelable(true)
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.cancel()
        }

        val alert = dialogBuilder.create()
        alert.setTitle("Person Info")
        alert.show()

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        Advertiser.subscribe(this, AdvertisementType.POSTS_LOADED)
    }

    override fun onPause() {
        super.onPause()
        Advertiser.unSubscribe(this, AdvertisementType.POSTS_LOADED)
    }

    override fun receiveData(advertisement: Advertisement<List<PostResponse>>) {
        runOnUiThread {
            Progressbar.visibility = View.GONE
            postAdapter.replaceData(advertisement.data)
        }
    }

    private fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    private fun tryToConnect() {
        if (isOnline()) {
            runOnUiThread {
                Progressbar.visibility = View.VISIBLE
            }
            MessageController.fetchPosts()
        } else {
            showNetworkAlert()
        }
    }

    private fun readFromCache() {
        //todo implement
//        if (false){
//            MessageController.fetchPosts()
//        }
//        else{
        showCacheAlert()
//        }
    }

    private fun showNetworkAlert() {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("No internet connection.")
            .setCancelable(false)
            .setPositiveButton("Continue without internet") { _, _ ->
                readFromCache()
            }
            .setNegativeButton("try again for connect") { _, _ ->
                tryToConnect()
            }.setNeutralButton("exit programme") { _, _ ->
                finish()
            }


        val alert = dialogBuilder.create()
        alert.setTitle("Network Manager")
        alert.show()
    }

    private fun showCacheAlert() {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("You have not any saved data or your data have has been expired.")
            .setCancelable(false)
            .setPositiveButton("Try to connect internet") { _, _ ->
                tryToConnect()
            }
            .setNegativeButton("exit programme") { _, _ ->
                finish()
            }


        val alert = dialogBuilder.create()
        alert.setTitle("Cache Manager")
        alert.show()
    }


    inner class PostAdapter(private var postList: ArrayList<PostResponse>) : RecyclerView.Adapter<PostViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PostViewHolder(LayoutInflater.from(this@PostsActivity).inflate(R.layout.post_item, null))

        override fun getItemCount() = postList.size

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.titleTextView).text = postList[position].title
            holder.itemView.findViewById<TextView>(R.id.bodyTextView).text = postList[position].body
            holder.itemView.setOnClickListener {
                val myIntent = Intent(baseContext, CommentsActivity::class.java)
                myIntent.putExtra("postId", postList[position].id)
                startActivity(myIntent)
            }
        }

        fun replaceData(posts: List<PostResponse>) {
            postList.clear()
            postList.addAll(posts)
            notifyDataSetChanged()
        }
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
