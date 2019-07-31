package com.camelkyhn.springtodolistapi.controller;

import com.camelkyhn.springtodolistapi.middleware.bases.IController;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.user.UserDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.user.UserFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.User;
import com.camelkyhn.springtodolistapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController implements IController<User, UserDto, UserFilterDto> {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/Get/{id}")
    public Result<User> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping("/List")
    public Result<List<User>> list(UserFilterDto filterDto) {
        return service.list(filterDto);
    }

    @Override
    @PostMapping("/Create")
    public Result<User> create(@RequestBody @Valid UserDto dto, BindingResult bindingResult) {
        return service.create(dto, bindingResult);
    }

    @Override
    @PutMapping("/Update/{id}")
    public Result<User> update(@PathVariable Long id, @RequestBody @Valid UserDto dto, BindingResult bindingResult) {
        return service.update(id, dto, bindingResult);
    }

    @Override
    @DeleteMapping("/Delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}