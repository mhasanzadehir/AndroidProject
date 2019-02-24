package ir.sharif.androidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ir.sharif.androidproject.model.Item
import ir.sharif.androidproject.repository.DataRepository
import ir.sharif.androidproject.R
import ir.sharif.androidproject.utils.loadUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.android.synthetic.main.item_layout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearButton.setOnClickListener { clearList() }
        refreshButton.setOnClickListener { refreshList() }
        fetchButton.setOnClickListener { fillList() }
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

    private fun bindView(datas: ArrayList<Item>) =
        datas.forEach { list.addView(createView(it)) }

    private fun createView(item: Item) : View {
        val view:View = LayoutInflater.from(this).inflate(R.layout.item_layout, null)
        view.titleTextView.text = item.title
        view.subtitleTextView.text = item.subTitle
        view.image.loadUrl(item.image)
        return view
    }

}

