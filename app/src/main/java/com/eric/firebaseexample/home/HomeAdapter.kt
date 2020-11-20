package com.eric.firebaseexample.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eric.firebaseexample.data.Article
import com.eric.firebaseexample.databinding.ItemHomeViewHolderBinding

class HomeAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback) {
    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Product]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Product]
     */
    class OnClickListener(val clickListener: (product: Article) -> Unit) {
        fun onClick(product: Article) = clickListener(product)
    }

    class HomeViewHolder(private var binding: ItemHomeViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, onClickListener: OnClickListener) {

            binding.article = article
            binding.root.setOnClickListener { onClickListener.onClick(article) }
            binding.executePendingBindings()
        }
    }



    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        private const val ITEM_VIEW_TYPE_TITLE            = 0x00
        private const val ITEM_VIEW_TYPE_PRODUCT_FULL     = 0x01
        private const val ITEM_VIEW_TYPE_PRODUCT_COLLAGE  = 0x02
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(ItemHomeViewHolderBinding.inflate(layoutInflater, parent, false))

    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HomeViewHolder -> {
                holder.bind((getItem(position)), onClickListener)
            }
        }
    }

//    override fun getItemViewType(position: Int): Int {
//        return when (getItem(position)) {
//            is HomeItem.Title -> ITEM_VIEW_TYPE_TITLE
//            is HomeItem.FullProduct -> ITEM_VIEW_TYPE_PRODUCT_FULL
//            is HomeItem.CollageProduct -> ITEM_VIEW_TYPE_PRODUCT_COLLAGE
//        }
//    }
}