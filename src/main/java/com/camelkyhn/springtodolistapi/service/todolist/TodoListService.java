package com.camelkyhn.springtodolistapi.service.todolist;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseService;
import com.camelkyhn.springtodolistapi.middleware.bases.IService;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.todolist.TodoListDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.todolist.TodoListFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.TodoList;
import com.camelkyhn.springtodolistapi.middleware.entities.User;
import com.camelkyhn.springtodolistapi.middleware.enums.Status;
import com.camelkyhn.springtodolistapi.middleware.exceptions.EmptyIdException;
import com.camelkyhn.springtodolistapi.middleware.exceptions.NotFoundException;
import com.camelkyhn.springtodolistapi.service.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService extends BaseService<ITodoListRepository, TodoList> implements IService<TodoList, TodoListDto, TodoListFilterDto> {

    private final ITodoListRepository repository;
    private final IUserRepository userRepository;

    @Autowired
    public TodoListService(ITodoListRepository repository, IUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Result<TodoList> get(Long id) {
        Result<TodoList> result = new Result<>();
        try {
            if (id == null || id <= 0) {
                throw new EmptyIdException(TodoListService.class.getSimpleName());
            }

            Optional<TodoList> todoList = repository.findById(id);
            if (!todoList.isPresent()) {
                throw new NotFoundException(TodoList.class.getSimpleName());
            }

            result.Success(todoList.get());
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<List<TodoList>> list(TodoListFilterDto filterDto) {
        Result<List<TodoList>> result = new Result<>();
        try {
            result.Success(repository.findAll());
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<TodoList> create(@Valid TodoListDto dto) {
        Result<TodoList> result = new Result<>();
        try {
            TodoList todoList = new TodoList();
            Optional<User> user = userRepository.findById(dto.getAssignedUserId());
            if (!user.isPresent()) {
                throw new NotFoundException(User.class.getSimpleName());
            }

            todoList.setName(dto.getName());
            todoList.setAssignedUser(user.get());
            result.Success(repository.save(todoList));
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<TodoList> update(@Valid TodoListDto dto) {
        Result<TodoList> result = new Result<>();
        try {
            Optional<TodoList> existingTodoList = repository.findById(dto.getId());
            if (!existingTodoList.isPresent()) {
                throw new NotFoundException(TodoList.class.getSimpleName());
            }

            Optional<User> user = userRepository.findById(dto.getAssignedUserId());
            if (!user.isPresent()) {
                throw new NotFoundException(User.class.getSimpleName());
            }

            TodoList todoList = existingTodoList.get();
            todoList.setName(dto.getName());
            todoList.setAssignedUser(user.get());
            todoList.setStatus(dto.getStatus());
            todoList.setUpdatedDate(new Date());
            result.Success(repository.save(todoList));
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<Boolean> delete(Long id) {
        Result<Boolean> result = new Result<>();
        try {
            if (id == null || id <= 0) {
                throw new EmptyIdException(TodoListService.class.getSimpleName());
            }

            TodoList todoList = get(id).getData();
            if (todoList == null) {
                throw new NotFoundException(TodoList.class.getSimpleName());
            }

            todoList.setStatus(Status.Deleted);
            todoList.setUpdatedDate(new Date());
            repository.save(todoList);
            result.Success(true);
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }
}