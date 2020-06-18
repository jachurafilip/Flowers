package com.example.flowers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers.databinding.RecyclerItemFlowerModelBinding


class FlowerListFragment : Fragment() {

    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>
    private lateinit var listener: OnFlowerSelected

    companion object {

        fun newInstance(): FlowerListFragment {
            return FlowerListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFlowerSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnFlowerSelected.")
        }

        // Get dog names and descriptions.
        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)

        // Get dog images.
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_flower_list, container,
            false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = FlowerListAdapter(activity)
        return view
    }

    internal inner class FlowerListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerFlowerModelBinding =
                RecyclerItemFlowerModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerFlowerModelBinding.root, recyclerFlowerModelBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val flower = FlowerModel(imageResIds[position], names[position],
                descriptions[position], urls[position])
            viewHolder.setData(flower)
            viewHolder.itemView.setOnClickListener { listener.onFlowerSelected(flower) }
        }

        override fun getItemCount() = names.size
    }

    internal inner class ViewHolder constructor(itemView: View,
                                                private val recyclerItemFlowerListBinding:
                                                RecyclerItemFlowerModelBinding
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(flowerModel: FlowerModel) {
            recyclerItemFlowerListBinding.flowerModel = flowerModel
        }
    }

    interface OnFlowerSelected {
        fun onFlowerSelected(flowerModel: FlowerModel)
    }

}