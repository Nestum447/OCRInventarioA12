package com.nestor.ocrinventarioa12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nestor.ocrinventarioa12.databinding.ItemInventoryBinding

class InventoryAdapter(
    private val onDelete: (InventoryEntity) -> Unit
) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    private var items: List<InventoryEntity> = emptyList()

    fun submitList(list: List<InventoryEntity>) {
        items = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemInventoryBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInventoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.txtItem.text = item.text

        holder.binding.btnDelete.setOnClickListener {
            onDelete(item)
        }
    }

    override fun getItemCount() = items.size
}
