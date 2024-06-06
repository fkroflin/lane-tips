package hr.ferit.frankroflin.laneTips

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import androidx.navigation.navArgument
import hr.ferit.frankroflin.laneTips.data.LaneViewModel
import hr.ferit.frankroflin.laneTips.ui.LaneScreen
import hr.ferit.frankroflin.laneTips.ui.MainScreen
import hr.ferit.frankroflin.laneTips.ui.NoteScreen

object Routes{
    val screen_all_lanes = "lanes"
    val screen_lane_detail = "laneDetails/{laneId}"
    val screen_lane_notes   = "laneNotes/{laneId}"

    fun getLaneDetailsPath(laneId:Int) :String{
        if(laneId != null && laneId != -1){

            return "laneDetails/$laneId"
        }
        else{
            return "laneDetails/0"
        }
    }

    fun getLanesPath():String{
        return "lanes"
    }

    fun getNotesPath(laneId: Int): String {
        if (laneId != null && laneId != -1) {
            return "laneNotes/$laneId"
        } else {
            return "laneNotes/0"
        }
    }
}


@Composable
fun NavigationController(
    viewModel: LaneViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =
    Routes.screen_all_lanes) {
        composable(Routes.screen_all_lanes) {
            MainScreen(
                viewModel = viewModel,
                navigation = navController
            )
        }
        composable(
            Routes.screen_lane_detail,
            arguments = listOf(
                navArgument("laneId") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            backStackEntry.arguments?.getInt("laneId")?.let {
                LaneScreen(
                    viewModel = viewModel,
                    navigation = navController,
                    laneId = it
                )
            }
        }
        composable(
            Routes.screen_lane_notes,
            arguments = listOf(
                navArgument("laneId") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            backStackEntry.arguments?.getInt("laneId")?.let {
                NoteScreen(
                    viewModel = viewModel,
                    navigation = navController,
                    laneId = it
                )
            }
        }
    }
}