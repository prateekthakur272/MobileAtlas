package dev.prateekthakur.mobileatlas.ui.screens.countries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.prateekthakur.mobileatlas.domain.model.Country
import dev.prateekthakur.mobileatlas.ui.navigation.navController

@Composable
fun CountryCard(data: Country, modifier: Modifier = Modifier) {
    Surface(
        onClick = {
            navController.navigate("country/${data.iso2}")
        }
    ){
        Box(
            modifier = modifier
                .background(Color.Gray.copy(alpha = 0.1f))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
            ) {
                //            NetworkImage(
                //                "https://cdn.britannica.com/97/1597-050-008F30FA/Flag-India.jpg",
                //                modifier = modifier
                //                    .height(64.dp)
                //                    .clip(
                //                        RoundedCornerShape(corner = CornerSize(8.dp))
                //                    )
                //            )
                //            Spacer(modifier.size(16.dp))
                Column {
                    Text(data.name, style = MaterialTheme.typography.titleMedium)
                    Text(
                        "${data.iso3}/${data.iso2}",
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
                    )
                    Row {
                        Text(data.capital)
                    }
                }
            }
        }
    }
}