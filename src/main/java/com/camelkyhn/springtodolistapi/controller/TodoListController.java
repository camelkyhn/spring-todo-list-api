package com.camelkyhn.springtodolistapi.controller;

import com.camelkyhn.springtodolistapi.middleware.bases.IController;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.todolist.TodoListDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.todolist.TodoListFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.TodoList;
import com.camelkyhn.springtodolistapi.service.todolist.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/TodoList")
public class TodoListController implements IController<TodoList, TodoListDto, TodoListFilterDto> {
    private final TodoListService service;

    @Autowired
    public TodoListController(TodoListService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/Get/{id}")
    public Result<TodoList> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping("/List")
    public Result<List<TodoList>> list(TodoListFilterDto filterDto) {
        return service.list(filterDto);
    }

    @Override
    @PostMapping("/Create")
    public Result<TodoList> create(@RequestBody @Valid TodoListDto dto, BindingResult bindingResult) {
        return service.create(dto, bindingResult);
    }

    @Override
    @PutMapping("/Update/{id}")
    public Result<TodoList> update(@PathVariable Long id, @RequestBody @Valid TodoListDto dto, BindingResult bindingResult) {
        return service.update(id, dto, bindingResult);
    }

    @Override
    @DeleteMapping("/Delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}