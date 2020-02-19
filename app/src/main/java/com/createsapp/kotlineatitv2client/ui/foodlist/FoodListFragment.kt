package com.createsapp.kotlineatitv2client.ui.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.createsapp.kotlineatitv2client.R
import com.createsapp.kotlineatitv2client.adapter.MyFoodListaAdapter
import com.createsapp.kotlineatitv2client.common.Common

class FoodListFragment : Fragment() {

    private lateinit var foodListViewModel: FoodListViewModel

    var recycler_food_list: RecyclerView? = null
    var layoutAnimationController: LayoutAnimationController? = null

    var adapter: MyFoodListaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodListViewModel =
            ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_food_list, container, false)
        initView(root)
        foodListViewModel.getMutableFoodModelListData().observe(this, Observer {
            adapter = MyFoodListaAdapter(context!!, it)
            recycler_food_list!!.adapter = adapter
            recycler_food_list!!.layoutAnimation = layoutAnimationController
        })
        return root
    }

    private fun initView(root: View?) {
        recycler_food_list = root!!.findViewById(R.id.recycler_food_list)
        recycler_food_list!!.setHasFixedSize(true)
        recycler_food_list!!.layoutManager = LinearLayoutManager(context)

        layoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_item_from_left)

        (activity as AppCompatActivity).supportActionBar!!.title = Common.categorySelected!!.name
    }
}