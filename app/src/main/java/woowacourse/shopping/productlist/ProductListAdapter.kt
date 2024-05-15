package woowacourse.shopping.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import woowacourse.shopping.R
import woowacourse.shopping.databinding.ItemProductListBinding

class ProductListAdapter(
    private val onClick: ProductListContract.ViewAction,
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    private var items: MutableList<ProductUiModel> = mutableListOf()

    class ProductListViewHolder(
        private val binding: ItemProductListBinding,
        private val onClick: ProductListContract.ViewAction,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ProductUiModel) {
            with(binding) {
                tvProductListName.text = item.name
                tvProductListPrice.text =
                    itemView.context.getString(R.string.product_price_format, item.price)
                Glide.with(itemView.context).load(item.imageUrl).into(ivProductItem)
                root.setOnClickListener {
                    onClick.onProductClicked(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProductListViewHolder {
        val binding =
            ItemProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: ProductListViewHolder,
        position: Int,
    ) {
        return holder.onBind(items[position])
    }

    fun submitList(products: List<ProductUiModel>) {
        items.addAll(products)
    }
}