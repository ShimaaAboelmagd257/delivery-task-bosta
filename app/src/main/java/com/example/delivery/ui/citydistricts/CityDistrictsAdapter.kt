package com.example.delivery.ui.citydistricts

import android.R
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery.data.model.City
import com.example.delivery.data.model.CityDistricts
import com.example.delivery.databinding.CityDistrictRowBinding
import com.example.delivery.util.AppLogger
import java.util.logging.Logger

class CityDistrictsAdapter(private  val onItemClicked:(CityDistricts) ->Unit): ListAdapter<CityDistricts, CityDistrictsAdapter.MyViewHolder>(
    CityDiffUtil
) {


     class MyViewHolder(private val binding: CityDistrictRowBinding): RecyclerView.ViewHolder(binding.root) {
       private var currentAdapter :ArrayAdapter<String>? = null
        fun bind(cityDistrict: CityDistricts, onItemClicked: (CityDistricts) -> Unit) {
            binding.apply {
                cityNameTextView.text = cityDistrict.cityName

                arrowImageView.setImageResource(
                    if (cityDistrict.isExpanded) R.drawable.arrow_up_float
                    else R.drawable.arrow_down_float
                )
                districtSpinner.visibility = if (cityDistrict.isExpanded) View.VISIBLE else View.GONE
                if (currentAdapter == null || currentAdapter?.count != cityDistrict.districts.size) {
                    val districtAdapter = ArrayAdapter(
                        binding.root.context,
                        R.layout.simple_spinner_item,
                        cityDistrict.districts.map {
                            it.districtName
                        }
                    )

                    districtAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                    districtSpinner.adapter = districtAdapter
                }
                root.setOnClickListener{
                    onItemClicked(cityDistrict)
                }
                AppLogger.d("CityViewHolder Binding view for: ${cityDistrict.cityName}")
            }
        }


        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CityDistrictRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
          val item =getItem(position)
          holder.bind(item,onItemClicked)
        AppLogger.d( "Binding city: ${item.cityName}, Districts: ${item.districts}")
    }

    override fun getItemCount(): Int {
        AppLogger.d("")

        return super.getItemCount()
    }

    object CityDiffUtil : DiffUtil.ItemCallback<CityDistricts>(){
        override fun areItemsTheSame(oldItem: CityDistricts, newItem: CityDistricts): Boolean {
            return oldItem.cityId == newItem.cityId
        }

        override fun areContentsTheSame(oldItem: CityDistricts, newItem: CityDistricts): Boolean {
            return oldItem == newItem
        }
    }
}



