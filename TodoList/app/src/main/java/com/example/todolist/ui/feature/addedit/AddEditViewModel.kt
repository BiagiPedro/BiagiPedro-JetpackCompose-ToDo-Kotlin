import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoRepository
import com.example.todolist.domain.Todo
import com.example.todolist.ui.UiEvent
import com.example.todolist.ui.feature.addedit.AddEditEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val id: Long?,
    private val repository: TodoRepository
) : ViewModel() {

    private val _title = mutableStateOf("")
    val title: String get() = _title.value

    private val _description = mutableStateOf("")
    val description: String get() = _description.value

    private val _startTime = mutableStateOf("")
    val startTime: String get() = _startTime.value

    private val _endTime = mutableStateOf("")
    val endTime: String get() = _endTime.value

    private val _isCompleted = mutableStateOf(false)
    val isCompleted: Boolean get() = _isCompleted.value

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        if (id != null) {
            loadTodo(id)
        }
    }

    private fun loadTodo(id: Long) {
        viewModelScope.launch {
            val todo = repository.getBy(id) ?: return@launch
            _title.value = todo.title
            _description.value = todo.description ?: ""
            _startTime.value = todo.startTime ?: ""
            _endTime.value = todo.endTime ?: ""
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.TitleChanged -> _title.value = event.title
            is AddEditEvent.DescriptionChanged -> _description.value = event.description
            is AddEditEvent.StartTimeChanged -> _startTime.value = event.startTime
            is AddEditEvent.EndTimeChanged -> _endTime.value = event.endTime
            is AddEditEvent.Save -> saveTodo()
        }
    }

    private fun saveTodo() {
        viewModelScope.launch {
            val todo = Todo(
                id = id ?: 0,
                title = title,
                description = description,
                startTime = startTime,
                endTime = endTime,
                isCompleted = isCompleted
            )

            if (id == null) {
                repository.insert(title, description, id, startTime, endTime)
            } else {
                repository.updateCompleted(id, todo.isCompleted )
            }
            _uiEvent.emit(UiEvent.NavigateBack)
        }
    }
}
