package hr.ferit.frankroflin.laneTips.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import hr.ferit.frankroflin.laneTips.R
import hr.ferit.frankroflin.laneTips.data.Lane
import hr.ferit.frankroflin.laneTips.data.LaneViewModel
import hr.ferit.frankroflin.laneTips.ui.theme.LightTurq
import kotlinx.coroutines.tasks.await
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import hr.ferit.frankroflin.laneTips.ui.theme.Cream
import hr.ferit.frankroflin.laneTips.ui.theme.DarkBlue
import java.time.format.TextStyle


val customFont = FontFamily(Font(R.font.odibeesans))


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: LaneViewModel,
    navigation: NavController,
    laneId: Int
) {

    var notes by remember { mutableStateOf("")  }

    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .background(
                            color = LightTurq
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    BackButton {
                        navigation.navigateUp()
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                ){

                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .width(311.dp)
                                .height(217.dp),
                            painter = painterResource(id = R.drawable.noteborder),
                            contentDescription = null
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(231.dp)
                            .height(140.dp)
                    ){
                        val textFieldModifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .padding(8.dp)

                        TextField(
                            value = notes,
                            onValueChange = {notes = it},
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontSize = 18.sp,
                                color = Cream,
                                fontFamily = customFont
                            ),
                            singleLine = false,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = DarkBlue.copy(alpha = 0f),
                                focusedIndicatorColor = DarkBlue.copy(alpha = 0f),
                                unfocusedIndicatorColor = DarkBlue.copy(alpha = 0f),
                                disabledIndicatorColor = DarkBlue.copy(alpha = 0f)
                            ),
                            modifier = textFieldModifier,
                        label = {
                            Text(
                                text = "Start writing...",
                                color = Cream,
                                fontFamily = FontFamily(Font(R.font.odibeesans)),
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 14.sp
                                )
                                )
                        }
                        )
                    }
                }
                AddNote(){
                    viewModel.lanesData[laneId].notes = viewModel.lanesData[laneId].notes + notes
                    viewModel.updateLane(viewModel.lanesData[laneId])
                }

                Row (
                    modifier = Modifier
                        .width(300.dp)
                        .height(3.dp)
                        .background(color = DarkBlue)
                ){}
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(420.dp)
                        .background(
                            color = DarkBlue.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                        .padding(top = 10.dp)
                ){
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        items(viewModel.lanesData[laneId].notes.size){
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                                text = viewModel.lanesData[laneId].notes[it],
                                textAlign = TextAlign.Center,
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.odibeesans)),
                                    color = Cream
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddNote(
    onClick: ()->Unit = {}
){
    Row (
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Image(
            modifier = Modifier
                .width(130.dp)
                .height(52.dp)
                .clickable { onClick() },
            painter = painterResource(id = R.drawable.addnote),
            contentDescription = null
        )
    }
}


