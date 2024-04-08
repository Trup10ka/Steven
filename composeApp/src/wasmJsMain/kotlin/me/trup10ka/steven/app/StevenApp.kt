@file:Suppress("FunctionName")

package me.trup10ka.steven.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

import steven.composeapp.generated.resources.Res
import steven.composeapp.generated.resources.compose_multiplatform

@Composable
fun StevenApp()
{
    StevenCanvas()
}

@Composable
private fun StevenCanvas()
{
    MaterialTheme {
        MiddleColumn()
    }
}

@Composable
private fun MiddleColumn()
{
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Steven")
        TextField(value = "Enter invitation link", onValueChange = {} )
        Button( onClick = { println("Button clicked") }) {
            Text("Take me in")
        }

        /*AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }*/
    }
}