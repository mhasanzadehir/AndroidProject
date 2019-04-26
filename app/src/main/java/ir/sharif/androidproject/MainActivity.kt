package ir.sharif.androidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import com.orhanobut.logger.Logger
import ir.sharif.androidproject.database.posts.PostBean
import ir.sharif.androidproject.managers.StorageManager
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.models.Item
import ir.sharif.androidproject.utils.loadUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*

class MainActivity : AppCompatActivity(), Advertiser.AdvertiseListener<List<Item>> {
    private var dataOnScreen = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearButton.setOnClickListener { clearList() }
        refreshButton.setOnClickListener { refreshList() }
        fetchButton.setOnClickListener { fetchList() }
        if (savedInstanceState != null) {
            dataOnScreen = savedInstanceState.getParcelableArrayList("ON_SCREEN_DATA")
            bindView(dataOnScreen)
        }
    }

    override fun onResume() {
        super.onResume()
        Advertiser.subscribe(this, AdvertisementType.DATA_LOADED)
    }

    override fun onPause() {
        super.onPause()
        Advertiser.unSubscribe(this, AdvertisementType.DATA_LOADED)
    }

    override fun receiveData(advertisement: Advertisement<List<Item>>) {
        Logger.i("New Advertised Data Received: ${advertisement.data}")
        dataOnScreen.addAll(advertisement.data)
        // Update View
        runOnUiThread {
            bindView(advertisement.data)
        }
    }

    private fun clearList() {
        listView.removeAllViews()
        dataOnScreen.clear()
        MessageController.clearList()
    }

    private fun refreshList() {
        listView.removeAllViews()
        dataOnScreen.clear()
        MessageController.fetch(fromCache = true)
    }

    private fun fetchList() {
        val dataSize = StorageManager.readFromPref()
        if (listView.size < dataSize) {
            runOnUiThread {
                listView.removeAllViews()
                runOnUiThread {
                    bindView((listView.size + 1..dataSize).map { Item(it.toString(), "From Cache", "") })
                }
            }
        }
        MessageController.fetch(fromCache = false)
    }

    private fun bindView(dataList: List<Item>) {
        dataList.forEach { listView.addView(createView(it)) }
        Logger.i("size of list view " + listView.size)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("ON_SCREEN_DATA", dataOnScreen)
    }

    private fun createView(item: Item): View {
        val view = LayoutInflater.from(this).inflate(R.layout.item_layout, null)
        view.titleTextView.text = item.title
        view.subtitleTextView.text = item.subTitle
        view.image.loadUrl(item.image)
        when {
            item.subTitle == "From Cache" -> view.image.setImageResource(R.drawable.cache_image)
            item.subTitle == "From Refresh" -> view.image.setImageResource(R.drawable.storage_image)
            item.subTitle == "From Server" -> view.image.setImageResource(R.drawable.server_image)
        }
        return view
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }
}
