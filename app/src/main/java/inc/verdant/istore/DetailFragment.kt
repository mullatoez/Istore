package inc.verdant.istore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import coil.load
import inc.verdant.istore.databinding.FragmentDetailBinding
import java.text.NumberFormat


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: MainViewModel
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val productImage = args.product.imageFile
        val productName = args.product.name
        val productDescription = args.product.description
        val productPrice = args.product.price

        binding.productImageDetails.load(productImage)
        binding.productNameDetails.text = productName
        binding.productDescriptionDetails.text = productDescription
        binding.productPriceDetails.text =
            NumberFormat.getCurrencyInstance().format(productPrice).toString()

        binding.btnAddToCart.setOnClickListener {
            Toast.makeText(
                context,
                "You clicked ${viewModel.selectedProduct.value?.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
        return binding.root
    }
}