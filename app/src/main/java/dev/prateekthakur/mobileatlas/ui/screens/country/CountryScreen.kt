package dev.prateekthakur.mobileatlas.ui.screens.country

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.prateekthakur.mobileatlas.ui.composables.NetworkSvg
import java.text.NumberFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(modifier: Modifier = Modifier, viewModel: CountryViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    var expanded by rememberSaveable { mutableStateOf(false) }
    var expandedCities by rememberSaveable { mutableStateOf(false) }
    var expandedStates by rememberSaveable { mutableStateOf(false) }

    val rotation by animateFloatAsState(if (expanded) 180f else 0f, label = "")
    val rotationCities by animateFloatAsState(if (expandedCities) 180f else 0f, label = "")
    val rotationStates by animateFloatAsState(if (expandedStates) 180f else 0f, label = "")

    val populationData = state.population?.populationCounts?.reversed() ?: emptyList()
    val cities = state.cities ?: emptyList()
    val states = state.states ?: emptyList()

    val dataToShow = mutableListOf(
        "ISO3 Code: ${state.iso3}",
        "ISO2 Code: ${state.iso2}"
    )

    state.currency?.let { dataToShow.add("Currency: $it") }
    state.position?.let { dataToShow.add("Location: ${it.lat}, ${it.lon}") }
    if (cities.isNotEmpty()) dataToShow.add("Number of cities: ${cities.size}")
    if (states.isNotEmpty()) dataToShow.add("Number of states: ${states.size}")
    state.dialCode?.let { dataToShow.add("Country code/Dial code: $it") }

    LaunchedEffect(Unit) {
        viewModel.getCountryData("NG")
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Country") })
    }) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            item {
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    listOf(
                                        Color.Blue.copy(alpha = 0.5f),
                                        Color.Red.copy(alpha = 0.5f)
                                    )
                                )
                            )
                    )
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    ) {
                        state.flagUrl?.let {
                            NetworkSvg(
                                it, cornerRadius = 8, modifier = Modifier.height(140.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.size(16.dp))
            }

            item {
                Text("Nigeria", style = MaterialTheme.typography.titleLarge)
                Text(
                    "Nairobi",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(alpha = 0.5f)
                )
                Spacer(modifier = modifier.size(16.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        dataToShow.forEach {
                            AnimatedVisibility(visible = true) {
                                Text(it)
                            }
                        }
                    }
                }
                Spacer(modifier = modifier.size(16.dp))
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded }
                        .padding(16.dp)
                ) {
                    Text(
                        "Population (${populationData.size})",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand",
                        modifier = Modifier
                            .rotate(rotation)
                            .size(30.dp)
                    )
                }
            }

            if (expanded) {
                items(populationData.size) { index ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            populationData[index].year.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text("➡️")
                        Text(populationData[index].value.formatNumber())
                    }
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedStates = !expandedStates }
                        .padding(16.dp)
                ) {
                    Text(
                        "States (${states.size})",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand",
                        modifier = Modifier
                            .rotate(rotationStates)
                            .size(30.dp)
                    )
                }
            }

            if (expandedStates) {
                items(states.size) { index ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            "🌁 ${states[index].name} - ${states[index].stateCode}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedCities = !expandedCities }
                        .padding(16.dp)
                ) {
                    Text(
                        "Cities (${cities.size})",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand",
                        modifier = Modifier
                            .rotate(rotationCities)
                            .size(30.dp)
                    )
                }
            }

            if (expandedCities) {
                items(cities.size) { index ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            "🏙️ ${cities[index]}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

fun Number.formatNumber(): String {
    return NumberFormat.getNumberInstance(Locale.ENGLISH).format(this)
}