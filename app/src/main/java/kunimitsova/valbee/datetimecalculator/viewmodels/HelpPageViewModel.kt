package kunimitsova.valbee.datetimecalculator.viewmodels

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class HelpPageViewModel: ViewModel() {
    private val _uiState = mutableStateOf(HelpScreenUiState(
        isLoading = true,
        isError = false,
        howToUseString = null
    ))
    val uiState: State<HelpScreenUiState> = _uiState

    fun loadHelpData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputStream = context.assets.open("HowToUse.html")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                val string = String(buffer)
                launch(Dispatchers.Main) {
                    _uiState.value = uiState.value.copy(
                        isLoading = false,
                        isError = false,
                        howToUseString = string
                    )
                }
            } catch (e: IOException) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    _uiState.value = uiState.value.copy(
                        isLoading = false,
                        isError = true,
                        howToUseString = "ERROR help text did not load : \n" +
                                e.printStackTrace().toString()
                    )
                }
            }
        }
    }
}
data class HelpScreenUiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val howToUseString: String?
)