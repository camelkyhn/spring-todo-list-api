package com.camelkyhn.springtodolistapi.controller;

import com.camelkyhn.springtodolistapi.middleware.bases.IController;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.todo.TodoDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.todo.TodoFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.Todo;
import com.camelkyhn.springtodolistapi.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Todo")
public class TodoController implements IController<Todo, TodoDto, TodoFilterDto> {
    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/Get/{id}")
    public Result<Todo> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping("/List")
    public Result<List<Todo>> list(TodoFilterDto filterDto) {
        return service.list(filterDto);
    }

    @Override
    @PostMapping("/Create")
    public Result<Todo> create(@RequestBody @Valid TodoDto dto, BindingResult bindingResult) {
        return service.create(dto, bindingResult);
    }

    @Override
    @PutMapping("/Update/{id}")
    public Result<Todo> update(@PathVariable Long id, @RequestBody @Valid TodoDto dto, BindingResult bindingResult) {
        return service.update(id, dto, bindingResult);
    }

    @Override
    @DeleteMapping("/Delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}