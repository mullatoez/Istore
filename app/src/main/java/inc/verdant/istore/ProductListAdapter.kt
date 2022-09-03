package inc.verdant.istore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import inc.verdant.istore.data.Product
import inc.verdant.istore.databinding.ProductItemLayoutBinding

class ProductListAdapter(
    private val products: List<Product>,
    private val listener: ItemClick
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        with(holder.binding) {
            productName.text = product.name
            productImage.load(product.imageFile) {
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(product)
        }

    }

    override fun getItemCount() = products.size

    interface ItemClick {
        fun onItemClick(product: Product)
    }

}