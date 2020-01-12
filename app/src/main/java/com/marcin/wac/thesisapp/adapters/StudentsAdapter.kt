package com.marcin.wac.thesisapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.models.UserModel
import kotlinx.android.synthetic.main.row_user.view.*

class StudentsAdapter(private val dataset: List<UserModel>,
                    private val mUserClicked: (UserModel) -> Unit) : RecyclerView.Adapter<StudentsAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row_user, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        viewHolder.bindView(dataset[position], mUserClicked)
    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(user: UserModel, mUserClicked: (UserModel) -> Unit) {
            with(view) {
                row_user_name.text = "${user.name} ${user.surname} (${user.department})"
                setOnClickListener { mUserClicked(user) }
            }
        }
    }
}