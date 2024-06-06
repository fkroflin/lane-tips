package hr.ferit.frankroflin.laneTips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.frankroflin.laneTips.data.LaneViewModel
import hr.ferit.frankroflin.laneTips.ui.theme.LaneTipsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<LaneViewModel>()
        setContent {
            NavigationController(viewModel)
        }
    }
}