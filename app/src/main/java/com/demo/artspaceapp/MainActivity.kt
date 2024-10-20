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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

    // Arrays to hold character images
    val imageIds = arrayOf(
        R.drawable.captainamerica,
        R.drawable.ironman,
        R.drawable.thor,
        R.drawable.hulk,
        R.drawable.drstrange
    )

    // Arrays to hold character names
    val name = arrayOf(
        "Captain America",
        "Iron Man",
        "Thor",
        "Hulk",
        "Doctor Strange"
    )

    // Mutable state to keep track of the currently displayed artwork index
    var CharacterIndex by remember { mutableStateOf(0) }


    tasks(
        imagePainter = painterResource(imageIds[CharacterIndex]),
        name = name[CharacterIndex],
        title = stringResource(R.string.title),
        onNextClick = {
            // Update the index to the next artwork (cycle back to 0 if at the end)
            CharacterIndex = (CharacterIndex+1)%name.size
        },
        onPrevClick = {
            // Update the index to the previous artwork (cycle back to the last if at the start)
            CharacterIndex = (CharacterIndex-1 + name.size)%name.size
        }
    )
}

@Composable
fun tasks(
    imagePainter: Painter,
    name: String,
    title: String,
    onNextClick: () -> Unit, // Click handler for the "Next" button
    onPrevClick: () -> Unit, // Click handler for the "Prev" button
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
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = title,
                    fontSize = 36.sp, // Larger font for emphasis
                    color = Color.White, // White text color for contrast
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold, // Make the text bold
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient( // Gradient background
                                colors = listOf(Color(0xFF2196F3), Color(0xFF03DAC5)) // Blue to Teal
                            )
                        )
                        .padding(16.dp) // Padding for space around text
                        .shadow(8.dp), // Shadow to give depth
                )


                Spacer(modifier = Modifier.height(50.dp)) // Adjust top spacing
                Image(
                        painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(500.dp) // Adjust size to better fit the layout
                    .padding(8.dp) // Add some padding around the image
                    .background(
                        Color(0xFFE0F7FA),
                        shape = RoundedCornerShape(16.dp)
                    ) // White background for contrast
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
                    .background(
                        Color(0xFF1976D2),
                        shape = RoundedCornerShape(8.dp)
                    ) // Background for the text
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
                onClick = onPrevClick
            )
            Spacer(modifier = Modifier.width(8.dp))

            StyledButton(
                text = "Next",
                onClick = onNextClick
                )
        }
    }
}

@Composable //Styling next and previous buttons
fun StyledButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier ) {
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