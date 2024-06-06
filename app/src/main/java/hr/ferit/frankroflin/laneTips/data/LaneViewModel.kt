package hr.ferit.frankroflin.laneTips.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class LaneViewModel: ViewModel() {
    private val db = Firebase.firestore
    val lanesData = mutableStateListOf<Lane>()
    init {
        fetchDatabaseData()
    }
    private fun fetchDatabaseData() {
        db.collection("Lanes")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val lane = data.toObject(Lane::class.java)
                    if (lane != null) {
                        lane.id = data.id
                        lanesData.add(lane)
                    }
                }
            }
    }
    fun updateLane(lane: Lane) {
        db.collection("Lanes")
            .document(lane.id)
            .set(lane)
    }
}