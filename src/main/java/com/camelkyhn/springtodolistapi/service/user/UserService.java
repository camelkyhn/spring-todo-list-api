package com.camelkyhn.springtodolistapi.service.user;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseService;
import com.camelkyhn.springtodolistapi.middleware.bases.IService;
import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.dtos.user.UserDto;
import com.camelkyhn.springtodolistapi.middleware.dtos.user.UserFilterDto;
import com.camelkyhn.springtodolistapi.middleware.entities.Role;
import com.camelkyhn.springtodolistapi.middleware.entities.User;
import com.camelkyhn.springtodolistapi.middleware.enums.Status;
import com.camelkyhn.springtodolistapi.middleware.exceptions.EmptyIdException;
import com.camelkyhn.springtodolistapi.middleware.exceptions.NotFoundException;
import com.camelkyhn.springtodolistapi.service.role.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends BaseService<IUserRepository, User> implements IService<User, UserDto, UserFilterDto> {

    private final IUserRepository repository;
    private final IRoleRepository roleRepository;

    @Autowired
    public UserService(IUserRepository repository, IRoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Result<User> get(Long id) {
        Result<User> result = new Result<>();
        try {
            if (id == null || id <= 0) {
                throw new EmptyIdException(UserService.class.getSimpleName());
            }

            Optional<User> user = repository.findById(id);
            if (!user.isPresent()) {
                throw new NotFoundException(User.class.getSimpleName());
            }

            result.Success(user.get());
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<List<User>> list(UserFilterDto filterDto) {
        Result<List<User>> result = new Result<>();
        try {
            Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (filterDto.getFirstName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), filterDto.getFirstName())));
                }

                if (filterDto.getLastName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"), filterDto.getLastName())));
                }

                if (filterDto.getUsername() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("username"), filterDto.getUsername())));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            List<User> resultList = applyPagination(repository, filterDto, specification);
            result.Success(resultList, filterDto);
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<User> create(@Valid UserDto dto) {
        Result<User> result = new Result<>();
        try {
            User user = new User(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getPassword());
            result.Success(repository.save(user));
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    @Override
    public Result<User> update(@Valid UserDto dto) {
        Result<User> result = new Result<>();
        try {
            Optional<User> existingUser = repository.findById(dto.getId());
            if (!existingUser.isPresent()) {
                throw new NotFoundException(User.class.getSimpleName());
            }
            User user = existingUser.get();

            if (!dto.getRoleIds().isEmpty()) {
                updateIfItHasRole(user, dto.getRoleIds());
            }

            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            user.setStatus(dto.getStatus());
            user.setUpdatedDate(new Date());
            result.Success(repository.save(user));
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }

    private User updateIfItHasRole(User user, List<Long> roleIds) throws NotFoundException {
        List<Role> userRoles = user.getRoles();
        for (Long roleId : roleIds) {
            if (roleId > 0) {
                Optional<Role> existingRole = roleRepository.findById(roleId);
                if (!existingRole.isPresent()) {
                    throw new NotFoundException(Role.class.getSimpleName());
                }
                userRoles.add(existingRole.get());
            }
        }
        user.setRoles(userRoles);
        return user;
    }

    @Override
    public Result<Boolean> delete(Long id) {
        Result<Boolean> result = new Result<>();
        try {
            if (id == null || id <= 0) {
                throw new EmptyIdException(UserService.class.getSimpleName());
            }

            User user = get(id).getData();
            if (user == null) {
                throw new NotFoundException(User.class.getSimpleName());
            }

            user.setStatus(Status.Deleted);
            user.setUpdatedDate(new Date());
            repository.save(user);
            result.Success(true);
        } catch (Exception exception) {
            result.Error(exception);
        }
        return result;
    }
}