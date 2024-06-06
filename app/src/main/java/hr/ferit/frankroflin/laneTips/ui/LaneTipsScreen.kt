package hr.ferit.frankroflin.laneTips.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import hr.ferit.frankroflin.laneTips.R
import hr.ferit.frankroflin.laneTips.Routes
import hr.ferit.frankroflin.laneTips.data.LaneViewModel
import hr.ferit.frankroflin.laneTips.ui.theme.Cream
import hr.ferit.frankroflin.laneTips.ui.theme.DarkBlue
import hr.ferit.frankroflin.laneTips.ui.theme.LightBlue
import hr.ferit.frankroflin.laneTips.ui.theme.LightBlue2
import hr.ferit.frankroflin.laneTips.ui.theme.LightBlue20
import hr.ferit.frankroflin.laneTips.ui.theme.LightTurq
import hr.ferit.frankroflin.laneTips.ui.theme.Moss
import hr.ferit.frankroflin.laneTips.ui.theme.Pink80
import hr.ferit.frankroflin.laneTips.ui.theme.Smoke
import hr.ferit.frankroflin.laneTips.ui.theme.Turq



@Composable
fun LaneScreen(
    viewModel: LaneViewModel,
    navigation: NavController,
    laneId: Int
){
    val lane = viewModel.lanesData[laneId]
    Column (){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .background(
                            color = LightTurq
                        ),
                    horizontalArrangement = Arrangement.Start
                ){
                    BackButton(){
                        navigation.navigateUp()
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    LaneTitle(
                        laneTitle = lane.title,
                        imageResourceL = lane.imageL,
                        imageResourceR = lane.imageR
                    )

                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        item {
                            LaneDetailsLeft(
                                imageResource = R.drawable.earlyphase,
                                laneDetails = "EARLY PHASE",
                                color = LightBlue20,
                                laneDescritpion = lane.earlyPhase
                            )
                            if(lane.gank != ""){
                                LaneDetailsRight(
                                    imageResource = R.drawable.gank,
                                    laneDetails = "GANKING",
                                    color = LightBlue20,
                                    laneDescritpion = lane.gank
                                )
                            }
                            if(lane.camps != ""){
                                LaneDetailsLeft(
                                    imageResource = R.drawable.camps,
                                    laneDetails = "CAMPS",
                                    color = LightBlue20,
                                    laneDescritpion = lane.camps
                                )
                            }
                            if(lane.wave != ""){
                                LaneDetailsRight(
                                    imageResource = R.drawable.wave,
                                    laneDetails = "WAVE CONTROL",
                                    color = LightBlue20,
                                    laneDescritpion = lane.wave
                                )
                            }
                            if(lane.splitPush != ""){
                                LaneDetailsLeft(
                                    imageResource = R.drawable.push,
                                    laneDetails = "SPLIT PUSH",
                                    color = LightBlue20,
                                    laneDescritpion = lane.splitPush
                                )
                            }
                            if(lane.roam != ""){
                                LaneDetailsLeft(
                                    imageResource = R.drawable.roam,
                                    laneDetails = "ROAMING",
                                    color = LightBlue20,
                                    laneDescritpion = lane.roam
                                )
                            }
                            LaneDetailsRight(
                                imageResource = R.drawable.lategame,
                                laneDetails = "MID & LATE GAME",
                                color = LightBlue20,
                                laneDescritpion = lane.mlGame
                            )
                            if(lane.warding != ""){
                                LaneDetailsLeft(
                                    imageResource = R.drawable.vision,
                                    laneDetails = "WARDING",
                                    color = LightBlue20,
                                    laneDescritpion = lane.warding
                                )
                            }
                            NoteButton(){
                                navigation.navigate(Routes.getNotesPath(laneId))
                            }
                        }
                    }


                }
            }

        }
    }
}

@Composable
fun LaneTitle(
    laneTitle: String,
    imageResourceL: String,
    imageResourceR: String
){
    Box(
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.border),
            contentDescription = null)
        Row (
            modifier = Modifier
                .padding(horizontal = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .width(65.dp)
                    .height(65.dp),
                painter = rememberAsyncImagePainter(model = imageResourceL),
                contentDescription = null,

                )
            Text(
                text = laneTitle,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightBlue,
                    fontFamily = FontFamily(Font(R.font.odibeesans))
                )
            )
            Image(
                modifier = Modifier
                    .width(65.dp)
                    .height(65.dp),
                painter = rememberAsyncImagePainter(model = imageResourceR),
                contentDescription = null
            )
        }
    }
}

@Composable
fun LaneDetailsLeft(
    @DrawableRes imageResource: Int,
    laneDetails: String,
    color: Color,
    laneDescritpion: String

){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(330.dp)
            .height(160.dp)
            .padding(vertical = 8.dp)
            .background(
                color = DarkBlue.copy(alpha = 0.5f),
                shape = RoundedCornerShape(size = 15.dp)
            )

    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier
                    .width(330.dp)
                    .padding(8.dp)
                    .background(
                        color = Moss,
                        shape = RoundedCornerShape(size = 15.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    modifier = Modifier
                        .padding(horizontal = 6.dp),
                    text = laneDetails,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 26.sp,
                        color = color,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.odibeesans))
                    )
                )
                Image(
                    modifier = Modifier
                        .width(65.dp)
                        .height(40.dp),
                    painter = painterResource(id = imageResource),
                    contentDescription = null
                )
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 3.dp)
            ){
                item {
                    Text(
                        modifier = Modifier
                            .width(310.dp),
                        text = laneDescritpion,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Cream,
                            fontFamily = FontFamily(Font(R.font.odibeesans))
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun LaneDetailsRight(
    @DrawableRes imageResource: Int,
    laneDetails: String,
    color: Color,
    laneDescritpion: String
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(330.dp)
            .height(160.dp)
            .padding(vertical = 8.dp)
            .background(
                color = DarkBlue.copy(alpha = 0.5f),
                shape = RoundedCornerShape(size = 15.dp)
            )

    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier
                    .width(330.dp)
                    .padding(8.dp)
                    .background(
                        color = Moss,
                        shape = RoundedCornerShape(size = 15.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                Image(
                    modifier = Modifier
                        .width(65.dp)
                        .height(40.dp),
                    painter = painterResource(id = imageResource),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 6.dp),
                    text = laneDetails,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 26.sp,
                        color = color,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.odibeesans))
                    )
                )
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 3.dp)
            ){
                item {
                    Text(
                        modifier = Modifier
                            .width(310.dp),
                        text = laneDescritpion,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Cream,
                            fontFamily = FontFamily(Font(R.font.odibeesans))
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun NoteButton(
    onClick: ()->Unit = {},
){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(15.dp)
    ) {
        Image(
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .clickable { onClick() },
            painter = painterResource(id = R.drawable.notebutton),
            contentDescription = null,
        )
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit = {}
){
    Image(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .padding(6.dp)
            .clickable { onClick() },
        painter = painterResource(id = R.drawable.back),
        contentDescription = null,
        alignment = Alignment.CenterStart
    )
}
