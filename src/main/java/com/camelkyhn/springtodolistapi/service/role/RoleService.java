package com.camelkyhn.springtodolistapi.service.role;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseService;
import com.camelkyhn.springtodolistapi.middleware.bases.IService;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.role.RoleDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.role.RoleFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.Role;
import com.camelkyhn.springtodolistapi.middleware.enums.Status;
import com.camelkyhn.springtodolistapi.middleware.exceptions.EmptyIdException;
import com.camelkyhn.springtodolistapi.middleware.exceptions.InvalidModelStateException;
import com.camelkyhn.springtodolistapi.middleware.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends BaseService<IRoleRepository, Role> implements IService<Role, RoleDto, RoleFilterDto> {

    private final IRoleRepository repository;

    @Autowired
    public RoleService(IRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<Role> get(Long id) {
        Result<Role> result = new Result<>();
        try {
            if (id == null || id <= 0) {
                throw new EmptyIdException(RoleService.class.getSimpleName());
            }

            Optional<Role> role = repository.findById(id);
            if (!role.isPresent()) {
                throw new NotFoundException(Role.class.getSimpleName());
            }

            result.Success(role.get());
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<List<Role>> list(RoleFilterDto filterDto) {
        Result<List<Role>> result = new Result<>();
        try {
            Specification<Role> specification = (Specification<Role>) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (filterDto.getName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + filterDto.getName() + "%")));
                }

                predicates.addAll(applyBaseFilters(filterDto, criteriaBuilder, root));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            List<Role> resultList = applyPagination(repository, filterDto, specification);
            result.Success(resultList, filterDto);
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<Role> create(RoleDto dto, BindingResult bindingResult) {
        Result<Role> result = new Result<>();
        try {
            if (bindingResult.hasErrors()) {
                throw new InvalidModelStateException(bindingResult.getAllErrors().get(0));
            }

            Role role = new Role(dto.getName(), dto.getStatus());
            result.Success(repository.save(role));
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<Role> update(Long id, RoleDto dto, BindingResult bindingResult) {
        Result<Role> result = new Result<>();
        try {
            if (bindingResult.hasErrors()) {
                throw new InvalidModelStateException(bindingResult.getAllErrors().get(0));
            }

            Optional<Role> existingRole = repository.findById(id);
            if (!existingRole.isPresent()) {
                throw new NotFoundException(Role.class.getSimpleName());
            }

            Role role = existingRole.get();
            role.setName(dto.getName());
            role.setStatus(dto.getStatus());
            result.Success(repository.save(role));
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
                throw new EmptyIdException(RoleService.class.getSimpleName());
            }

            Role role = get(id).getData();
            if (role == null) {
                throw new NotFoundException(Role.class.getSimpleName());
            }

            role.setStatus(Status.Deleted);
            repository.save(role);
            result.Success(true);
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }
}