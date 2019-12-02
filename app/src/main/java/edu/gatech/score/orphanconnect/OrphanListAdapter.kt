package edu.gatech.score.orphanconnect

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.gatech.score.orphanconnect.database.domain.Orphan
import edu.gatech.score.orphanconnect.utils.GlideApp
import kotlinx.android.synthetic.main.orphan_item.view.*

class OrphanListAdapter internal constructor(
        val context: Context
): RecyclerView.Adapter<OrphanListAdapter.OrphanViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var orphans = emptyList<Orphan>()

    fun setOrphans(orphans: List<Orphan>?) {
        this.orphans = orphans ?: emptyList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = orphans.size

    inner class OrphanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePhoto: ImageView = itemView.img_profile_photo
        val nameAndAge: TextView = itemView.text_name_age
        val refugeeCampOrVillage: TextView = itemView.text_refugeeCamp_village
        val description: TextView = itemView.text_description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrphanViewHolder {
        val itemView = inflater.inflate(R.layout.orphan_item, parent, false)
        return OrphanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrphanViewHolder, position: Int) {
        val orphan = orphans[position]
        holder.nameAndAge.text = "${orphan.firstName} ${orphan.lastName}, ${orphan.age}"

        val refugeeCampAndVillageText = getRefugeeCampAndVillage(orphan.refugeeCamp, orphan.village)
        if (refugeeCampAndVillageText == null) {
            holder.refugeeCampOrVillage.visibility = View.GONE
        } else {
            holder.refugeeCampOrVillage.text = refugeeCampAndVillageText
            holder.refugeeCampOrVillage.visibility = View.VISIBLE
        }

        if (orphan.description.isNullOrBlank()) {
            holder.description.visibility = View.GONE
        } else {
            holder.description.text = orphan.description
            holder.description.visibility = View.VISIBLE
        }

        // Use Glide to set profile photo here
        if (!orphan.photoUrl.isNullOrEmpty()) {
            GlideApp.with(context)
                    .load(orphan.photoUrl)
                    .placeholder(R.drawable.profile_photo_placeholder)
                    .centerCrop()
                    .into(holder.profilePhoto)
        } else {
            GlideApp.with(context)
                    .load(R.drawable.profile_photo_placeholder)
                    .centerCrop()
                    .into(holder.profilePhoto)
        }
    }

    private fun getRefugeeCampAndVillage(refugeeCamp: String?, village: String?): String? =
            if (!refugeeCamp.isNullOrBlank()) {
                if (!village.isNullOrBlank())
                    "$refugeeCamp, $village"
                else
                    refugeeCamp
            } else {
                if (!village.isNullOrBlank())
                    village
                else
                    null
            }
}
