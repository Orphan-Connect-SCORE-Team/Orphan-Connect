package edu.gatech.score.orphanconnect.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.gatech.score.orphanconnect.R
import edu.gatech.score.orphanconnect.database.domain.Orphan
import kotlinx.android.synthetic.main.orphan_item.view.*

class OrphanListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<OrphanListAdapter.OrphanViewHolder>()  {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var orphans = emptyList<Orphan>() // Cached copy of orphans

    inner class OrphanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameAndAge = itemView.text_name_age
        val refugeeCampOrVillage = itemView.text_refugeeCamp_village
        val description = itemView.text_description
        val picture = itemView.img_profile_photo

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrphanViewHolder {
        val itemView = inflater.inflate(R.layout.orphan_item, parent, false)
        return OrphanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrphanViewHolder, position: Int) {
        val current = orphans[position]
        holder.nameAndAge.text = "${current.firstName} ${current.lastName}, ${current.age}"
        holder.refugeeCampOrVillage.text = if (!current.refugeeCamp.isNullOrBlank()) {
            if (!current.village.isNullOrBlank()) {
                "${current.refugeeCamp}, ${current.village}"
            }
            else {
                current.refugeeCamp
            }
        }
        else {
            if (!current.village.isNullOrBlank()) {
                current.village
            }
            else {
                ""
            }
        }
        holder.description.text = current.description ?: ""
        //holder.img implementation here
    }

    fun setOrphans(orphans: List<Orphan>) {
        this.orphans = orphans
        notifyDataSetChanged()
    }

    override fun getItemCount() = orphans.size
}
