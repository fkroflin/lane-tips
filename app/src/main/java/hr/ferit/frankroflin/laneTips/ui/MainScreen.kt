package hr.ferit.frankroflin.laneTips.ui

import androidx.annotation.DrawableRes
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.frankroflin.laneTips.R
import hr.ferit.frankroflin.laneTips.Routes
import hr.ferit.frankroflin.laneTips.data.LaneViewModel
import hr.ferit.frankroflin.laneTips.ui.theme.Cream
import hr.ferit.frankroflin.laneTips.ui.theme.Turq


@Composable
fun MainScreen(
    viewModel: LaneViewModel,
    navigation: NavController,
    i: Int = 1
){
    Column{
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
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(20.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ){
                    items(viewModel.lanesData.size){
                        if(viewModel.lanesData[it].title == "TOP: Summit Strategies" || viewModel.lanesData[it].title == "MID: Central Tactics" || viewModel.lanesData[it].title == "SUPPORT: Guardian"){
                            LaneDescriptionLeft(imageResource = viewModel.lanesData[it].laneIcon, laneDescription = viewModel.lanesData[it].laneDis){
                                navigation.navigate(Routes.getLaneDetailsPath(it))
                            }
                        }
                        else{
                            LaneDescriptionRight(imageResource = viewModel.lanesData[it].laneIcon, laneDescription = viewModel.lanesData[it].laneDis){
                                navigation.navigate(Routes.getLaneDetailsPath(it))
                            }
                        }
                    }
                }

            }
        }
    }
}



@Composable
fun LaneDescriptionRight(
    imageResource: String,
    laneDescription: String,
    onClick: ()->Unit = {}
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(330.dp)
            .height(100.dp)
            .background(
                color = Cream.copy(alpha = 0.6f),
                shape = RoundedCornerShape(size = 15.dp)
            )
            .clickable { onClick() }
    )
    {
        Row(
            horizontalArrangement=Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(6.dp)
        ){
            Box(
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .background(
                        color = Turq.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(size = 15.dp)
                    ),
                contentAlignment = Alignment.CenterEnd
            ){
                Image(
                    modifier= Modifier
                        .height(70.dp)
                        .width(70.dp)
                        .align(alignment = Alignment.Center),
                    painter = rememberAsyncImagePainter(model = imageResource),
                    contentDescription = null
                )
            }
            LazyColumn(){
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .width(200.dp),
                        text = laneDescription,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            fontFamily = FontFamily(Font(R.font.odibeesans))
                        )

                    )
                }
            }
        }
    }
}

@Composable
fun LaneDescriptionLeft(
    imageResource: String,
    laneDescription: String,
    onClick: ()->Unit = {}
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(330.dp)
            .height(100.dp)
            .background(
                color = Cream.copy(alpha = 0.6f),
                shape = RoundedCornerShape(size = 15.dp)
            )
            .clickable { onClick() }
    )
    {
        Row(
            horizontalArrangement=Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(6.dp)
        ){
            LazyColumn(){
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .width(200.dp),
                        text = laneDescription,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            fontFamily = FontFamily(Font(R.font.odibeesans))
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .background(
                        color = Turq.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(size = 15.dp)
                    ),
                contentAlignment = Alignment.CenterEnd
            ){
                Image(
                    modifier= Modifier
                        .height(70.dp)
                        .width(70.dp)
                        .align(alignment = Alignment.Center),
                    painter = rememberAsyncImagePainter(model = imageResource),
                    contentDescription = null
                )
            }
        }
    }

}
