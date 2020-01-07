package com.marcin.wac.thesisapp.modules.lecturer

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.adapters.ThesisAdapter
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_lecturer_main.*
import javax.inject.Inject

interface LecturerMainView{
    fun setThesisList(thesisList: List<ThesisModel>)
    fun setNewThesisClickListener()
    fun startAddNewThesisActivity()
    fun startThesisDetailActivity()

    fun showNoThesisLayout()
    fun hideNoThesisLayout()
    fun showLoadingView()
    fun hideLoadingView()
}

class LecturerMainActivity: BaseActivity(), LecturerMainView {
    @Inject
    lateinit var presenter: LecturerMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)

    }

    override fun setThesisList(thesisList: List<ThesisModel>) {
        val adapter = ThesisAdapter(thesisList) {onThesisClick(it)}
        lecturer_main_thesis_recycler.layoutManager = LinearLayoutManager(this)
        lecturer_main_thesis_recycler.adapter = adapter
    }

    private fun onThesisClick(thesis: ThesisModel) {
        presenter.onThesisClicked(thesis)
    }

    override fun setNewThesisClickListener() {
        lecturer_main_new_thesis.setOnClickListener {

        }
    }

    override fun startAddNewThesisActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startThesisDetailActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoThesisLayout() {
        lecturer_main_no_thesis_found.visibility = View.VISIBLE
        lecturer_main_thesis_recycler.visibility = View.GONE
    }

    override fun hideNoThesisLayout() {
        lecturer_main_no_thesis_found.visibility = View.GONE
        lecturer_main_thesis_recycler.visibility = View.VISIBLE
    }

    override fun showLoadingView() {
        lecturer_main_loading.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        lecturer_main_loading.visibility = View.GONE
    }

}