package com.example.sundevstest.main.view

import android.R.color.holo_green_dark
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sundevstest.R
import com.example.sundevstest.detail.view.DetailActivity
import com.example.sundevstest.main.MainPresenter
import com.example.sundevstest.main.MainPresenterClass
import com.example.sundevstest.main.view.adapter.ComicAdapter
import com.example.sundevstest.main.view.adapter.ComicClickListener
import com.example.sundevstest.pojo.ComicDetail
import com.example.sundevstest.pojo.Result
import com.example.sundevstest.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, ComicClickListener {

    private var mPresenter: MainPresenter? = null
    private var mComicAdapter: ComicAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = MainPresenterClass(this)
        mPresenter?.onCreate()
        mPresenter?.requestListOfComics()
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

    /*****************************************
     * MainView Interface
     */
    override fun showProgressBar(show: Boolean) {
        pb_main.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showErrorMsg(msg: String) {
        errorMsg.visibility = View.VISIBLE
        errorMsg.setText(msg)
    }

    override fun showToast(msg: String) {
        Util.showToast(this,msg)
    }

    override fun loadComics(results: ArrayList<Result>) {
        buildRecycler()
    }

    override fun enableElementsUI(enable: Boolean) {
        grid.isEnabled = enable
        list.isEnabled = enable
        main_rv.isEnabled = enable
    }

    fun changeToList(view: View) {
        Util.visualization = Util.LIST
        buildRecycler()
    }


    fun changeToGrid(view: View) {
        Util.visualization = Util.GRID
        buildRecycler()
    }

    private fun buildRecycler() {
        main_rv.visibility = View.VISIBLE

        if (Util.visualization == Util.LIST){
            main_rv.layoutManager = LinearLayoutManager(this)
        }else{
            main_rv.layoutManager = GridLayoutManager(this,3)
        }

        mComicAdapter = ComicAdapter(mPresenter?.getResults()!!,this)
        main_rv.adapter = mComicAdapter
    }

    override fun loadDetailResult(results: ComicDetail?) {
        if (results != null){
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra(ComicDetail::class.java.name,results)
            startActivity(intent)
        }
    }

    override fun showProgresBarComic(show: Boolean) {
        pbComic.visibility = if (show) View.VISIBLE else View.GONE
    }

    /****************************************
     * ComicClickListener
     */
    override fun onClickComicListener(result: Result) {
        if (pbComic.visibility == View.GONE)
            mPresenter?.requestDetailComic(result)
    }
}
