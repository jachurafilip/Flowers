package com.example.flowers

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.FlowerDAO
import com.example.flowers.databinding.RecyclerItemFlowerModelBinding
import java.util.*
import kotlin.collections.ArrayList


class FlowerListFragment : Fragment() {

    private  var ids: MutableList<Long> = ArrayList()
    private  var names: MutableList<String> = ArrayList()
    private  var dates: MutableList<Date> = ArrayList()
    private  var frequencies: MutableList<Long> = ArrayList()
    private lateinit var listener: OnFlowerSelected
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()

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

        // Get names and descriptions.
        val resources = context.resources
        val allPlants = flowerDao.getAllFlowers()

        for (flower in allPlants)
        {
            ids.add(flower.flowerId)
            names.add(flower.name)
            dates.add(flower.lastWatering)
            frequencies.add(flower.frequency)


        }
    }


//    override fun onConfigurationChanged(newConfig: Configuration): Unit {
//        super.onConfigurationChanged(newConfig)
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//          ...
//        } else {
//          ...
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_flower_list, container,
            false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)


        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
        }else {
            recyclerView.layoutManager = GridLayoutManager(activity, 1)
        }

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
            val flower = FlowerModel( ids[position],names[position],
                dates[position], frequencies[position])
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