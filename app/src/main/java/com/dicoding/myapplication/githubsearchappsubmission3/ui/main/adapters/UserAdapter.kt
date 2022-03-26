package com.dicoding.myapplication.githubsearchappsubmission3.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.myapplication.githubsearchappsubmission3.databinding.ItemRowBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val list = ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: com.dicoding.myapplication.githubsearchappsubmission3.background.UserData) {
            binding.apply {
                Glide.with(itemView)
                    .load(userData.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(ivItemPhoto)
                tvUsername.text = userData.login
            }
        }
    }
    fun setList(users: ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(list[holder.bindingAdapterPosition])
        }

    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: com.dicoding.myapplication.githubsearchappsubmission3.background.UserData)
    }


}


