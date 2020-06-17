package com.example.flowers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flowers.databinding.FragmentFlowerDetailsBinding



class FlowerDetailsFragment : Fragment() {

    companion object {

        private const val FLOWERMODEL = "model"

        fun newInstance(flowerModel: FlowerModel): FlowerDetailsFragment {
            val args = Bundle()
            args.putSerializable(FLOWERMODEL, flowerModel)
            val fragment = FlowerDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentFlowerDetailsBinding =
            FragmentFlowerDetailsBinding.inflate(inflater, container, false)

        val model = arguments!!.getSerializable(FLOWERMODEL) as FlowerModel
        fragmentFlowerDetailsBinding.dogModel = model
        model.text = String.format(getString(R.string.description_format), model.description, model.url)
        return fragmentFlowerDetailsBinding.root
    }


}

