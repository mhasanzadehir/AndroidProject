package ir.sharif.androidproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ir.sharif.androidproject.models.Item
import ir.sharif.androidproject.models.Notification
import ir.sharif.androidproject.models.NotificationCenter
import ir.sharif.androidproject.models.NotificationType
import ir.sharif.androidproject.repository.DataRepository
import ir.sharif.androidproject.utils.loadUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*

class MainActivity : AppCompatActivity(), NotificationCenter.NotificationCenterDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearButton.setOnClickListener { clearList() }
        refreshButton.setOnClickListener { refreshList() }
        fetchButton.setOnClickListener { fillList() }
    }

    override fun onResume() {
        super.onResume()
        NotificationCenter.subscribe(this, NotificationType.DATA_LOADED)
    }

    override fun onPause() {
        super.onPause()
        NotificationCenter.unSubscribe(this, NotificationType.DATA_LOADED)
    }

    override fun receiveData(notification: Notification) {
        Log.i(TAG, notification.content)
    }

    private fun clearList() {
        DataRepository.clear()
        list.removeAllViews()
    }

    private fun refreshList() {
        DataRepository.refresh()
        list.removeAllViews()
        bindView(DataRepository.getData())
    }

    private fun fillList() =
        bindView(DataRepository.fetchNewData())

    private fun bindView(dataList: ArrayList<Item>) =
        dataList.forEach { list.addView(createView(it)) }

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
