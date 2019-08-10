package br.com.hivecode.notes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.hivecode.notes.data.entity.Repo
import br.com.hivecode.notes.databinding.ItemReposBinding
import br.com.hivecode.notes.viewmodel.RepoViewModel

class ReposAdapter(val viewModel: RepoViewModel, val reposList : MutableList<Repo> = mutableListOf()) : RecyclerView.Adapter<ReposAdapter.ReposViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding = ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.viewModel = this.viewModel
        return ReposViewHolder(binding)
    }

    override fun getItemCount() = reposList.size

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bindView(reposList[position])
    }

    fun add(item: Repo){
        reposList.add(item)
        notifyDataSetChanged()
    }

    fun add(items: MutableList<Repo>){
        reposList.clear()
        reposList.addAll(items)
        notifyDataSetChanged()
    }

    class ReposViewHolder(private val binding: ItemReposBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(item: Repo) = with(binding){
            repo = item
        }
    }
}
