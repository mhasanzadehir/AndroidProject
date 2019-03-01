package ir.sharif.androidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import ir.sharif.androidproject.models.Item
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.repository.DataRepository
import ir.sharif.androidproject.utils.loadUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*

class MainActivity : AppCompatActivity(), Advertiser.AdvertiseListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearButton.setOnClickListener { clearList() }
        refreshButton.setOnClickListener { refreshList() }
        fetchButton.setOnClickListener { fetchList() }
    }

    override fun onResume() {
        super.onResume()
        Advertiser.subscribe(this, AdvertisementType.DATA_LOADED)
    }

    override fun onPause() {
        super.onPause()
        Advertiser.unSubscribe(this, AdvertisementType.DATA_LOADED)
    }

    override fun receiveData(advertisement: Advertisement) {
        Logger.i("New Advertised Data Received: ${advertisement.data}")
        // Update View
        runOnUiThread {
            bindView(DataRepository.transformData(advertisement.data))
        }
    }

    private fun clearList() = listView.removeAllViews()

    private fun refreshList() {
        listView.removeAllViews()
        MessageController.fetch(fromCache = true)
    }

    private fun fetchList() {
        MessageController.fetch(fromCache = false)
    }

    private fun bindView(dataList: List<Item>) =
        dataList.forEach { listView.addView(createView(it)) }

    private fun createView(item: Item): View {
        val view = LayoutInflater.from(this).inflate(R.layout.item_layout, null)
        view.titleTextView.text = item.title
        view.subtitleTextView.text = item.subTitle
        view.image.loadUrl(item.image)
        return view
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }
}
