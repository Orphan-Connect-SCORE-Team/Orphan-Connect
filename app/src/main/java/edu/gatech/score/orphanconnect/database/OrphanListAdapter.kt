package edu.gatech.score.orphanconnect.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.gatech.score.orphanconnect.R
import edu.gatech.score.orphanconnect.database.domain.Orphan

class OrphanListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<OrphanListAdapter.OrphanViewHolder>()  {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var orphans = emptyList<Orphan>() // Cached copy of orphans

    inner class OrphanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val orphanItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrphanViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return OrphanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrphanViewHolder, position: Int) {
        val current = orphans[position]
        holder.orphanItemView.text = "${current.firstName} ${current.lastName}, ${current.age}"
    }

    internal fun setWords(words: List<Orphan>) {
        this.orphans = orphans
        notifyDataSetChanged()
    }

    override fun getItemCount() = orphans.size
}
