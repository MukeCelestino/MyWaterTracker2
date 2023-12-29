package com.example.mywatertracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val ACCOUNT_EXTRA = "ACCOUNT_EXTRA"
private const val TAG = "AccountAdapter"

class AccountAdapter(private val context: Context, private val accounts: List<Accnt>) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val account = accounts[position]
        holder.bind(account)
    }

    override fun getItemCount() = accounts.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val rec = itemView.findViewById<TextView>(R.id.acc)
        private val amt = itemView.findViewById<TextView>(R.id.amnt)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(account: Accnt) {
            rec.text = account.timeOfDay
            amt.text = account.amountDrank

        }

        override fun onClick(v: View?) {
        }
    }
}