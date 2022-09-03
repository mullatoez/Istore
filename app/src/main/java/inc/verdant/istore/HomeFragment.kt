package inc.verdant.istore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import inc.verdant.istore.data.Product
import inc.verdant.istore.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), ProductListAdapter.ItemClick {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel

    private val onItemClick: (Product) -> Unit = {
        viewModel.selectedProduct.value = it
        findNavController().navigate(R.id.action_details)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        /*binding.homeFragRecyclerview.apply {
            val divider = DividerItemDecoration(context, GridLayoutManager(context,2))
            addItemDecoration(divider)
        }*/

        binding.homeFragRecyclerview.layoutManager = GridLayoutManager(context, 2)

        viewModel.products.observe(viewLifecycleOwner, Observer {
            binding.homeFragRecyclerview.adapter = ProductListAdapter(it,this@HomeFragment)
        })

        return binding.root
    }

    override fun onItemClick(product: Product) {
        Toast.makeText(context, "You clicked ${product.name}", Toast.LENGTH_SHORT).show()
        val action = HomeFragmentDirections.actionDetails(product)
        findNavController().navigate(action)
    }

}