package com.demo.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    tasks(
        imagePainter = painterResource(R.drawable.captainamerica),
        name = stringResource(R.string.ch_name)
    )
}

@Composable
fun tasks(
    imagePainter: Painter,
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2196F3), // Blue
                        Color(0xFF03DAC5)  // Teal
                    )
                )
            )
    ) {
        // Top section with image
        Box(
            modifier = Modifier
                .fillMaxHeight(0.75f) // Take 75% of the height for the image section
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(100.dp)) // Adjust top spacing
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(500.dp) // Adjust size to better fit the layout
                        .padding(16.dp) // Add some padding around the image
                        .background(Color.White, shape = RoundedCornerShape(16.dp)) // White background for contrast
                        .border(3.dp, Color(0xFF6200EE), RoundedCornerShape(16.dp)) // Optional: border around the image
                )
            }
        }

        // Spacer (optional)
        Spacer(modifier = Modifier.height(24.dp)) // Add some space between image and text

        // Bottom section with mobile number
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontSize = 32.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 8.dp) // Vertical padding for spacing
                    .background(Color(0xFF1976D2), shape = RoundedCornerShape(8.dp)) // Background for the text
                    .padding(horizontal = 16.dp) // Horizontal padding inside the background

            )
        }

        // Buttons Row
        Spacer(modifier = Modifier.height(24.dp)) // Space between text and buttons

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center, // Buttons arranged at the ends
            verticalAlignment = Alignment.CenterVertically
        ) {
            StyledButton(
                text = "Prev",
                onClick = {}
            )
            Spacer(modifier = Modifier.width(16.dp))

            StyledButton(
                text = "Next",
                onClick = {}
            )
        }
    }
}

@Composable //Styling next and prev buttons
fun StyledButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE), // Background color
            contentColor = Color.White // Text color
        ),
        shape = RoundedCornerShape(16.dp), // Rounded corners
        modifier = Modifier
            .padding(8.dp) // Padding inside the button
            .height(48.dp) // Height of the button
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}