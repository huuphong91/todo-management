package huu.phong.todomanagement.service.impl;

import huu.phong.todomanagement.dto.TodoDto;
import huu.phong.todomanagement.entity.Todo;
import huu.phong.todomanagement.exception.ResourceNotFoundException;
import huu.phong.todomanagement.repository.TodoRepository;
import huu.phong.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto to Todo entity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // save Todo entity to database
        Todo savedTodo = todoRepository.save(todo);



        // convert Todo entity to TodoDto
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());

        return  modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto updateTodoById(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setCompleted(Boolean.TRUE);

        Todo completedTodo = todoRepository.save(todo);

        return modelMapper.map(completedTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setCompleted(Boolean.FALSE);

        Todo incompleteTodo = todoRepository.save(todo);

        return modelMapper.map(incompleteTodo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();

        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }
}
