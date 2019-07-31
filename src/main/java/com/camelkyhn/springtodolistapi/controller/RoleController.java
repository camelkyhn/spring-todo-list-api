package com.camelkyhn.springtodolistapi.controller;

import com.camelkyhn.springtodolistapi.middleware.bases.IController;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.role.RoleDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.role.RoleFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.Role;
import com.camelkyhn.springtodolistapi.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Role")
public class RoleController implements IController<Role, RoleDto, RoleFilterDto> {
    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/Get/{id}")
    public Result<Role> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping("/List")
    public Result<List<Role>> list(RoleFilterDto filterDto) {
        return service.list(filterDto);
    }

    @Override
    @PostMapping("/Create")
    public Result<Role> create(@RequestBody @Valid RoleDto dto, BindingResult bindingResult) {
        return service.create(dto, bindingResult);
    }

    @Override
    @PutMapping("/Update/{id}")
    public Result<Role> update(@PathVariable Long id, @RequestBody @Valid RoleDto dto, BindingResult bindingResult) {
        return service.update(id, dto, bindingResult);
    }

    @Override
    @DeleteMapping("/Delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}