package com.annhienktuit.compose_demo.components

import androidx.annotation.Nullable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.annhienktuit.compose_demo.models.CountryInfo
import com.annhienktuit.compose_demo.ui.theme.ComposeDemoTheme

/**
 *Created by Nhien Nguyen on 7/3/2022
 */

@Composable
fun CardList(countries: List<CountryInfo> ) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = countries) { country ->
            CardItem(name = country.name, capital = country.capital)
        }
    }
}
@Composable
fun CardItem(name: String,@Nullable capital: String) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(if (expanded) 48.dp else 0.dp)

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp).clickable(
            onClick = { expanded = !expanded },
        )) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Black
                        ),
                        text = "Capital: $capital",
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        "Show less"
                    } else {
                        "Show more"
                    }

                )
            }
        }
    }
}

@Preview(showBackground = true, name = "MainActivity Preview", widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        CardItem("Hi", "Helu")
    }
}
