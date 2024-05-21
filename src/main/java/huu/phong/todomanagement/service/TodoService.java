package huu.phong.todomanagement.service;

import huu.phong.todomanagement.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodoById(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodoById(Long id, TodoDto todoDto);

    void deleteTodoById(Long id);

    TodoDto completeTodoById(Long id);

    TodoDto incompleteTodoById(Long id);
}
