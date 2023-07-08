package toss.next.drone0898.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import toss.next.drone0898.BR
import toss.next.drone0898.R
import toss.next.drone0898.data.model.CaffeItem
import toss.next.drone0898.data.model.Category
import toss.next.drone0898.databinding.CategoryItemBinding
import toss.next.drone0898.databinding.ListItemBinding

class MenuAdapter : RecyclerView.Adapter<MenuViewHolder>() {

    private lateinit var items: List<Any>

    var onClickCaffeItem: (CaffeItem) -> Unit = {}

    fun setItems(items: List<Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Category -> VIEW_TYPE_CATEGORY
            is CaffeItem -> VIEW_TYPE_ITEM
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VIEW_TYPE_CATEGORY -> {
                val view = layoutInflater.inflate(R.layout.category_item, parent, false)
                val binding = CategoryItemBinding.bind(view)
                MenuViewHolder(binding)
            }
            VIEW_TYPE_ITEM -> {
                val view = layoutInflater.inflate(R.layout.list_item, parent, false)
                val binding = ListItemBinding.bind(view)
                MenuViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        when (val item = items[position]) {
            is Category -> holder.bindCategory(item, position)
            is CaffeItem -> {
                holder.bindCaffeItem(item, position)
                holder.itemView.setOnClickListener{ onClickCaffeItem(item) }
            }
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun getItemCount() = items.size

    companion object {
        const val VIEW_TYPE_CATEGORY = 0
        const val VIEW_TYPE_ITEM = 1
    }
}

class MenuViewHolder(private val itemBinding: ViewDataBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindCategory(item: Category, position: Int){
        itemBinding.setVariable(BR.item,item)
        itemBinding.setVariable(BR.position,position)
    }
    fun bindCaffeItem(item: CaffeItem, position: Int){
        itemBinding.setVariable(BR.item,item)
        itemBinding.setVariable(BR.position,position)
    }
}