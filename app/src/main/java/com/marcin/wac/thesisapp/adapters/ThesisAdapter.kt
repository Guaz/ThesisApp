package com.marcin.wac.thesisapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.models.ThesisModel
import kotlinx.android.synthetic.main.row_thesis.view.*

class ThesisAdapter(private val dataset: List<ThesisModel>,
                       private val mThesisClicked: (ThesisModel) -> Unit) : RecyclerView.Adapter<ThesisAdapter.ThesisViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ThesisViewHolder {
        return ThesisViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row_thesis, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(viewHolder: ThesisViewHolder, position: Int) {
        viewHolder.bindView(dataset[position], mThesisClicked)
    }

    class ThesisViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(thesis: ThesisModel, mThesisClicked: (ThesisModel) -> Unit) {
            with(view) {
                row_thesis_title.text = thesis.title
                row_thesis_description.text = thesis.description
                row_thesis_skills.text = thesis.studentSkills
                setOnClickListener { mThesisClicked(thesis) }
            }
        }
    }
}