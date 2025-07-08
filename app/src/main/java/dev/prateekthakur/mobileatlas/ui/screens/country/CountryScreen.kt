package dev.prateekthakur.mobileatlas.ui.screens.country

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(
    iso2: String, modifier: Modifier = Modifier, viewModel: CountryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCountryData(iso2)
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Country") }) }) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (val result = state) {
                is CountryState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is CountryState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(result.error, color = MaterialTheme.colorScheme.error)
                    }
                }

                is CountryState.Success -> {
                    var showPopulation by rememberSaveable { mutableStateOf(false) }
                    var showStates by rememberSaveable { mutableStateOf(false) }
                    var showCities by rememberSaveable { mutableStateOf(false) }

                    val populationData =
                        result.population.populationCounts?.reversed() ?: emptyList()

                    val infoList = buildList {
                        add("ISO3 Code: ${result.country.iso3}")
                        add("ISO2 Code: ${result.country.iso2}")
                        add("Currency: ${result.currency}")
                        add("Location: ${result.position.lat}, ${result.position.lon}")
                        add("Country code/Dial code: ${result.dialCode}")
                        if (result.cities.isNotEmpty()) add("Number of cities: ${result.cities.size}")
                        if (result.states.isNotEmpty()) add("Number of states: ${result.states.size}")
                    }

                    LazyColumn(
                        contentPadding = padding,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        item {
                            HeaderImage(result.flagUrl)
                            Spacer(Modifier.height(16.dp))
                        }

                        item {
                            Text(result.country.name, style = MaterialTheme.typography.titleLarge)
                            Text(
                                result.country.capital,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black.copy(alpha = 0.5f)
                            )
                            Spacer(Modifier.height(16.dp))
                        }

                        item {
                            InfoCard(infoList)
                            Spacer(Modifier.height(16.dp))
                        }

                        item {
                            expandableSection(title = "Population (${populationData.size})",
                                expanded = showPopulation,
                                onToggle = { showPopulation = !showPopulation }) {
                                populationData.forEach {
                                    KeyValueRow(it.year.toString(), it.value.formatNumber())
                                }
                            }

                            expandableSection(title = "States (${result.states.size})",
                                expanded = showStates,
                                onToggle = { showStates = !showStates }) {
                                result.states.forEach {
                                    Text(
                                        text = "🌁 ${it.name} - ${it.stateCode}",
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                }
                            }

                            expandableSection(title = "Cities (${result.cities.size})",
                                expanded = showCities,
                                onToggle = { showCities = !showCities }) {
                                result.cities.forEach {
                                    Text(
                                        text = "🏙️ $it",
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderImage(flagUrl: String) {
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        listOf(Color.Blue.copy(alpha = 0.5f), Color.Red.copy(alpha = 0.5f))
                    )
                )
        )
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            NetworkSvg(flagUrl, cornerRadius = 8, modifier = Modifier.height(140.dp))
        }
    }
}

@Composable
fun InfoCard(infoList: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            infoList.forEach { Text(it) }
        }
    }
}

@Composable
fun expandableSection(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val rotation by animateFloatAsState(if (expanded) 180f else 0f, label = "")
    Column {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(16.dp)) {
            Text(
                title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .rotate(rotation)
                    .size(30.dp)
            )
        }

        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun KeyValueRow(key: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(key, style = MaterialTheme.typography.titleMedium)
        Text("➡️")
        Text(value, style = MaterialTheme.typography.titleMedium)
    }
}

fun Number.formatNumber(): String {
    return NumberFormat.getNumberInstance(Locale.ENGLISH).format(this)
}