package com.example.delivery.ui.citydistricts

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery.util.AppLogger
import com.example.delivery.R
import com.example.delivery.data.model.CityDistricts
import com.example.delivery.data.model.District

@Composable
 fun CityDistrictsView(viewModel: CityDistrictsViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    when(val responseState = state){

        is CityDistrictsStates.Loading ->{
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Text("Loading ..")
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

           /* val context = LocalContext.current
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
            )*/
            var searchQuery by remember { mutableStateOf("") }
            val cityDistricts = responseState.data

            val citiesWithSearch = cityDistricts.filter { city ->
                city.cityName.contains(searchQuery, ignoreCase = true) ||
                        city.districts.any { it.districtName.contains(searchQuery, ignoreCase = true) }
            }

            Column(modifier = Modifier.fillMaxSize()) {

                Text("Choose The delivery area", modifier = Modifier.padding(100.dp), fontWeight = FontWeight.Bold)

               // SearchBar(query = searchQuery, onQueryChanged = { searchQuery = it })

                CityDistrictList(
                    cities = citiesWithSearch,
                    query = searchQuery,
                    onCityClicked = { city ->
                        city.isExpanded = !city.isExpanded // Toggle the visibility of districts
                    }
                )
            }



        }
    }


}
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,

) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
            )
        },
        placeholder = {
            Text(text = "City/Area")
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        label = { Text(" City / Area") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { */
/* Do something with search query *//*
 })
    )
}
*/

@Composable
fun CityRow(city: CityDistricts, onClick: (CityDistricts) -> Unit) {
    val arrowRotation = if (city.isExpanded) 180f else 0f // Set rotation based on isExpanded state

    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { onClick(city) }
        ) {
            Text(
                text = city.cityName, modifier = Modifier.weight(1f).padding(end = 8.dp)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown arrow",
                modifier = Modifier
                    .rotate(arrowRotation) // Rotate the arrow
                    .padding(8.dp)
            )
        }
        if (city.isExpanded) {
            DistrictDropdownMenu(districts = city.districts)
        }
    }
}
@Composable
fun CityDistrictList(
    cities: List<CityDistricts>,
    query: String,
    onCityClicked: (CityDistricts) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cities) { city ->
            if (city.cityName.contains(query, ignoreCase = true) || city.districts.any {
                    it.districtName.contains(query, ignoreCase = true)
                }) {
                CityRow(city = city, onClick = onCityClicked)
            }
        }
    }
}
@Composable
fun DistrictDropdownMenu(districts: List<District>) {
    Card(        modifier = Modifier
        .padding(8.dp)
        ) {
    Row (modifier = Modifier.padding( 15.dp)) {

        districts.forEach { district ->
            Text(
                text = district.districtName,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = if (district.dropOffAvailability) "" else "Uncovered",
                modifier = Modifier.padding(4.dp)
            )


        }
    } }
}