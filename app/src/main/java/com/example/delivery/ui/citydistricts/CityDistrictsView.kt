package com.example.delivery.ui.citydistricts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color
import com.example.delivery.util.AppLogger
import com.example.delivery.R
import com.example.delivery.data.model.CityDistricts
import com.example.delivery.data.model.District

@Composable
 fun CityDistrictsView(viewModel: CityDistrictsViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    when(val responseState = state){

        is CityDistrictsStates.Loading ->{
            Column(modifier = Modifier.fillMaxSize().padding(40.dp), horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
                Text("Loading ..")
            }
        }


        is CityDistrictsStates.Error -> {
            Column(modifier = Modifier.fillMaxSize().padding(40.dp), horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id= R.drawable.access_time), contentDescription = "access_time", modifier = Modifier.size(200.dp), contentScale = ContentScale.Fit)

                Text("Api Access Error ..")
                AppLogger.d("CityDistrictsStates Error: ${responseState.error}")
            }
        }



        is CityDistrictsStates.Success -> {

            var searchQuery by remember { mutableStateOf("") }
            val cityDistricts = responseState.data

            Column(modifier = Modifier.fillMaxSize().padding(top = 30.dp)) {

                Text("Choose The pickup area", modifier = Modifier.padding(start= 30.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp)

               SearchBar(query = searchQuery, onQueryChanged = { searchQuery = it })

                CityDistrictList(
                    cities = cityDistricts,
                    query = searchQuery,
                    onCityClicked = { city ->
                        city.isExpanded = !city.isExpanded // Toggle the visibility of districts
                    }
                )
            }



        }
    }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,

) {
    TextField(value = query, onValueChange = onQueryChanged,
        trailingIcon  = {
            Icon(
                modifier = Modifier.padding(start = 3.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
            )
        },
        placeholder = {
            Text(text = "City/Area", color = Color.LightGray  )
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White   ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp).border(1.dp, Color.Black,  RoundedCornerShape(12.dp) )
    )
}






@Composable
fun CityDistrictList(
    cities: List<CityDistricts>,
    query: String,
    onCityClicked: (CityDistricts) -> Unit
) {

        val filteredCities =
            if (query.isBlank()) cities
            else {
                cities.mapNotNull { city ->
                    val matchingDistricts = city.districts.filter { district ->
                        district.districtName.contains(query, ignoreCase = true) || district.districtOtherName.contains(query, ignoreCase = true)
                    }
                    if (matchingDistricts.isNotEmpty()) {
                        city.copy(districts = matchingDistricts) // only keep matching districts
                    } else null
                }
            }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredCities) { city ->
                CityRow(
                    city = city,
                    searching = query.isNotBlank(),
                    onClick = onCityClicked
                )
            }
        }
    }

    @Composable
fun CityRow(city: CityDistricts, searching:Boolean ,onClick: (CityDistricts) -> Unit) {
    var dropDown by remember { mutableStateOf(false) }
    val arrowRotation = if (dropDown) 180f else 0f
    LaunchedEffect(searching) {
        if (searching) {
            dropDown = true
        }
    }
    Card(modifier = Modifier.fillMaxSize(),    colors = CardDefaults.cardColors(Color.White)) {
        Column {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    , verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = city.cityName, modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp))

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown arrow",
                    modifier = Modifier
                        .rotate(arrowRotation)
                        .padding(8.dp)
                        .clickable {
                            dropDown = !dropDown
                            onClick(city)
                        }
                )
            }
            if (dropDown) {
                DistrictDropdownMenu(districts = city.districts)
            }
        }
    }
}
@Composable
fun DistrictDropdownMenu(districts: List<District>) {
    Column (modifier = Modifier
        .background(Color(0xFFF5F5F5))){
        districts.forEach { district ->

            Card(
                modifier = Modifier.padding(5.dp).fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)   )

            ) {

                Row(modifier = Modifier.padding(5.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "${district.zoneName} -${district.districtName}",
                        modifier = Modifier.padding(4.dp).weight(1f)
                    )
                    if (!district.dropOffAvailability) Text(text =  "Uncovered", modifier = Modifier.padding(4.dp).background(color = Color(0xFFFFE5E5) , shape = RoundedCornerShape(4.dp)))

                }
            }
        }
    }
}


// toDo make recycler view
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