package com.annhienktuit.compose_demo

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.annhienktuit.compose_demo.ui.theme.ComposeDemoTheme
import com.annhienktuit.compose_demo.components.CardList
import com.annhienktuit.compose_demo.models.CountryInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainActivity : ComponentActivity() {
    private lateinit var countries: ArrayList<CountryInfo>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                MyApp()
            }
        }
    }
}
fun getCountryCode(context: Context): List<CountryInfo> {
    lateinit var jsonString: String
    try {
        jsonString = context.assets.open("data.json")
            .bufferedReader()
            .use { it.readText() }
    } catch (ioException: IOException) {
        Log.e("JSON PARSER: ", ioException.toString())
    }

    val listCountryType = object : TypeToken<List<CountryInfo>>() {}.type
    return Gson().fromJson(jsonString, listCountryType)
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        CardList(countries = getCountryCode(LocalContext.current))
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Loader()
            Text("Whassup!!",
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold
            ))
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick =  onContinueClicked
            ) {
                Text("Show me some shits")
            }
        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))
    LottieAnimation(
        composition,
        modifier = Modifier.size(200.dp),
        iterations = LottieConstants.IterateForever,
        speed = 5f

    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeDemoTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}