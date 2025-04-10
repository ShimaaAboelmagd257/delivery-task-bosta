package com.example.delivery.ui.theme.citydistricts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery.util.AppLogger
import com.example.delivery.R

@Composable
 fun CityDistrictsView(viewModel: CityDistrictsViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    when(val responseState = state){

        is CityDistrictsStates.Loading ->{
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(modifier = Modifier.padding(100.dp))
                Text("Loading ..", modifier = Modifier.padding(100.dp))
            }
        }

        is CityDistrictsStates.Error -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(100.dp)) {
                Image(painter = painterResource(id= R.drawable.access_time), contentDescription = "access_time", modifier = Modifier.size(200.dp), contentScale = ContentScale.Crop)

                Text("Api Access Error ..")
                AppLogger.d("CityDistrictsStates Error: ${responseState.error}")
            }
        }
        is CityDistrictsStates.Success -> {

            val context = LocalContext.current
            val cityDistricts = responseState.data

            AndroidView(
                factory = { ctx ->
                    RecyclerView(ctx).apply {
                        layoutManager = LinearLayoutManager(ctx)
                        adapter = CityDistrictsAdapter(
                            onItemClicked = { clickedCity -> viewModel.toggleExpansion(clickedCity.cityId)}

                        )
                    }

                }, update = { recyclerView ->
                    (recyclerView.adapter as? CityDistrictsAdapter)?.submitList(cityDistricts)
                },
                modifier = Modifier.fillMaxSize()
            )



        }
    }



}