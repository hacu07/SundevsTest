package com.example.sundevstest.detail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sundevstest.R
import com.example.sundevstest.detail.DetailPresenter
import com.example.sundevstest.detail.DetailPresenterClass
import com.example.sundevstest.detail.view.adapters.CreditAdapter
import com.example.sundevstest.pojo.ComicDetail
import com.example.sundevstest.pojo.Credit
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    private var mPresenter: DetailPresenter? = null

    private var mComicDetail: ComicDetail? =  null

    private var mCharactersAdapter: CreditAdapter? = null
    private var mConceptsAdapter: CreditAdapter? = null
    private var mLocationsAdapter: CreditAdapter? = null
    private var mTeamsAdapter: CreditAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mComicDetail = intent.extras!!.getSerializable(ComicDetail::class.java.name) as ComicDetail
        loadRecyclers()
        mPresenter = DetailPresenterClass(this)
        mPresenter?.onCreate()
        mPresenter?.getImages(mComicDetail!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

    private fun loadRecyclers() {
        if (mComicDetail != null){
            Glide.with(detail_img).load(mComicDetail!!.image.original_url).into(detail_img)
            loadCharacters()
            loadTeams()
            loadLocations()
            loadConcepts()
        }
    }

    private fun loadConcepts() {
        mComicDetail?.concept_credits.let {credits->
            mConceptsAdapter = CreditAdapter(credits!!)
            loadRecycler(rv_concepts,mConceptsAdapter)
        }
    }

    private fun loadLocations() {
        mComicDetail?.location_credits.let {credits->
            mLocationsAdapter = CreditAdapter(credits!!)
            loadRecycler(rv_locations,mLocationsAdapter)
        }
    }

    private fun loadTeams() {
        mComicDetail?.team_credits.let {credits->
            mTeamsAdapter = CreditAdapter(credits!!)
            loadRecycler(rv_teams,mTeamsAdapter)
        }
    }

    private fun loadCharacters() {
        mComicDetail?.character_credits.let {credits->
            mCharactersAdapter = CreditAdapter(credits!!)
            loadRecycler(rv_characters,mCharactersAdapter)
        }
    }

    private fun loadRecycler(recycler: RecyclerView, adapter: CreditAdapter?){
        recycler.layoutManager = GridLayoutManager(this,2)
        recycler.adapter = adapter
    }

    /******************************
     * DetailView Interface
     */
    override fun loadCreditImageCharacter(credit: Credit) {
        mCharactersAdapter?.update(credit)
    }

    override fun loadCreditImageTeam(credit: Credit) {
        mTeamsAdapter?.update(credit)
    }

    override fun loadCreditImageLocation(credit: Credit) {
        mLocationsAdapter?.update(credit)
    }

    override fun loadCreditImageConcept(credit: Credit) {
        mConceptsAdapter?.update(credit)
    }
}
