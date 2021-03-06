package com.camelkyhn.springtodolistapi.service.todo;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseService;
import com.camelkyhn.springtodolistapi.middleware.bases.IService;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.todo.TodoDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.todo.TodoFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.Todo;
import com.camelkyhn.springtodolistapi.middleware.entities.TodoList;
import com.camelkyhn.springtodolistapi.middleware.enums.Status;
import com.camelkyhn.springtodolistapi.middleware.exceptions.CanNotBeCompletedException;
import com.camelkyhn.springtodolistapi.middleware.exceptions.EmptyIdException;
import com.camelkyhn.springtodolistapi.middleware.exceptions.InvalidModelStateException;
import com.camelkyhn.springtodolistapi.middleware.exceptions.NotFoundException;
import com.camelkyhn.springtodolistapi.service.todolist.ITodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService extends BaseService<ITodoRepository, Todo> implements IService<Todo, TodoDto, TodoFilterDto> {

    private final ITodoRepository repository;
    private final ITodoListRepository todoListRepository;

    @Autowired
    public TodoService(ITodoRepository repository, ITodoListRepository todoListRepository) {
        this.repository = repository;
        this.todoListRepository = todoListRepository;
    }

    @Override
    public Result<Todo> get(Long id) {
        Result<Todo> result = new Result<>();
        try {
            if (id == null || id <= 0) {
                throw new EmptyIdException(TodoService.class.getSimpleName());
            }

            Optional<Todo> todo = repository.findById(id);
            if (!todo.isPresent()) {
                throw new NotFoundException(Todo.class.getSimpleName());
            }

            result.Success(todo.get());
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    public Result<List<Todo>> list(TodoFilterDto filterDto) {
        Result<List<Todo>> result = new Result<>();
        try {
            Specification<Todo> specification = (Specification<Todo>) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (filterDto.getName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + filterDto.getName() + "%")));
                }

                if (filterDto.getCompleted() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("completed"), filterDto.getCompleted())));
                }

                if (filterDto.getExpired() != null) {
                    if (filterDto.getExpired()) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("deadline"), new Date())));
                    } else {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("deadline"), new Date())));
                    }
                }

                if (filterDto.getTodoListId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("todoList").get("id"), filterDto.getTodoListId())));
                }

                if (filterDto.getDependentTodoId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("dependentTodo").get("id"), filterDto.getDependentTodoId())));
                }

                predicates.addAll(applyBaseFilters(filterDto, criteriaBuilder, root));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            List<Todo> resultList = applyPagination(repository, filterDto, specification);
            result.Success(resultList, filterDto);
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<Todo> create(TodoDto dto, BindingResult bindingResult) {
        Result<Todo> result = new Result<>();
        try {
            if (bindingResult.hasErrors()) {
                throw new InvalidModelStateException(bindingResult.getAllErrors().get(0));
            }

            Todo todo = new Todo();
            Optional<TodoList> todoList = todoListRepository.findById(dto.getTodoListId());
            if (!todoList.isPresent()) {
                throw new NotFoundException(TodoList.class.getSimpleName());
            }

            if (dto.getDependentTodoId() != null) {
                Optional<Todo> existingTodo = repository.findById(dto.getDependentTodoId());
                if (!existingTodo.isPresent()) {
                    throw new NotFoundException(Todo.class.getSimpleName());
                }
                todo.setDependentTodo(existingTodo.get());
            }

            todo.setName(dto.getName());
            todo.setDescription(dto.getDescription());
            todo.setDeadline(dto.getDeadline());
            todo.setTodoList(todoList.get());
            todo.setStatus(dto.getStatus());
            result.Success(repository.save(todo));
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<Todo> update(Long id, TodoDto dto, BindingResult bindingResult) {
        Result<Todo> result = new Result<>();
        try {
            if (bindingResult.hasErrors()) {
                throw new InvalidModelStateException(bindingResult.getAllErrors().get(0));
            }

            Optional<Todo> existingTodo = repository.findById(id);
            if (!existingTodo.isPresent()) {
                throw new NotFoundException(Todo.class.getSimpleName());
            }
            Todo todo = existingTodo.get();

            Optional<TodoList> todoList = todoListRepository.findById(dto.getTodoListId());
            if (!todoList.isPresent()) {
                throw new NotFoundException(TodoList.class.getSimpleName());
            }

            if (dto.getDependentTodoId() != null) {
                Optional<Todo> existingDependedTodo = repository.findById(dto.getDependentTodoId());
                if (!existingDependedTodo.isPresent()) {
                    throw new NotFoundException(Todo.class.getSimpleName());
                }
                todo.setDependentTodo(existingDependedTodo.get());

                if (dto.isCompleted() && !todo.getDependentTodo().isCompleted()) {
                    throw new CanNotBeCompletedException(Todo.class.getSimpleName());
                }
            }

            todo.setName(dto.getName());
            todo.setDescription(dto.getDescription());
            todo.setDeadline(dto.getDeadline());
            todo.setTodoList(todoList.get());
            todo.setCompleted(dto.isCompleted());
            todo.setStatus(dto.getStatus());
            result.Success(repository.save(todo));
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
                throw new EmptyIdException(TodoService.class.getSimpleName());
            }

            Todo todo = get(id).getData();
            if (todo == null) {
                throw new NotFoundException(Todo.class.getSimpleName());
            }

            Optional<Todo> requiredTodo = repository.findTodoDependentToThisId(id);
            if (requiredTodo.isPresent()) {
                Todo temp = requiredTodo.get();
                temp.setDependentTodo(null);
                repository.save(temp);
            }

            todo.setStatus(Status.Deleted);
            repository.save(todo);
            result.Success(true);
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }
}