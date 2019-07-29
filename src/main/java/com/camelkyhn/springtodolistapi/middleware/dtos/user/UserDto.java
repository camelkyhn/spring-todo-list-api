package com.camelkyhn.springtodolistapi.middleware.dtos.user;

import com.camelkyhn.springtodolistapi.middleware.bases.BaseDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class UserDto extends BaseDto {
    @NotEmpty
    @Length(max = 50, min = 3)
    private String firstName;

    @NotEmpty
    @Length(max = 50, min = 3)
    private String lastName;

    @NotEmpty
    @Length(max = 50, min = 3)
    private String username;

    @NotEmpty
    @Length(max = 100, min = 6)
    private String password;

    private List<Long> roleIds = new ArrayList<>();

    public UserDto() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}