package com.example.mywatertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.coroutines.launch

private const val TAG = "AccountFragment/"

class AccountFragment : Fragment() {
    private val accounts = mutableListOf<Accnt>()
    private lateinit var accountsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_account, container, false)
        val layoutManager = LinearLayoutManager(context)
        accountsRecyclerView = view.findViewById(R.id.records)
        accountsRecyclerView.layoutManager = layoutManager
        accountsRecyclerView.setHasFixedSize(true)
        val accountAdapter = AccountAdapter(view.context, accounts)
        accountsRecyclerView.adapter = accountAdapter
        lifecycleScope.launch {
            (activity?.application as WaterApplication).db.accountDao().getAll()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        Accnt(
                            entity.timeOfDay,
                            entity.amountDrank
                        )
                    }.also { mappedList ->
                        accounts.clear()
                        accounts.addAll(mappedList)
                        accountAdapter.notifyDataSetChanged()
                    }
                }
        }
        return view
    }

    companion object {
        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }

}