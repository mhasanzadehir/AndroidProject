package ir.sharif.androidproject

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ir.sharif.androidproject.model.Item
import ir.sharif.androidproject.repository.DataRepository
import kotlinx.android.synthetic.main.activity_main.*
import ir.sharif.androidproject.R

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
        datas.forEach { _ -> list.addView(LayoutInflater.from(this).inflate(R.layout.item_layout, list)) }

}
