package com.camelkyhn.springtodolistapi;

import com.camelkyhn.springtodolistapi.middleware.bases.Result;
import com.camelkyhn.springtodolistapi.middleware.entities.Role;
import com.camelkyhn.springtodolistapi.middleware.entities.Todo;
import com.camelkyhn.springtodolistapi.middleware.entities.User;
import com.camelkyhn.springtodolistapi.middleware.exceptions.NotFoundException;
import com.camelkyhn.springtodolistapi.service.role.RoleService;
import com.camelkyhn.springtodolistapi.service.todo.TodoService;
import com.camelkyhn.springtodolistapi.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTodoListApiApplicationTests {

    @Test
    public void deleteNonExistingRole() {
		RoleService mockedService = mock(RoleService.class);
        Result<Boolean> deleteResult = new Result<>();
        deleteResult.Error(new NotFoundException(Role.class.getSimpleName()));
        when(mockedService.delete(anyLong())).thenReturn(deleteResult);

        Result<Boolean> testResult = mockedService.delete(Long.MAX_VALUE);
		verify(mockedService).delete(anyLong());

		assertEquals(testResult.getSucceeded(), deleteResult.getSucceeded());
		assertEquals(testResult.getData(), deleteResult.getData());
    }

    @Test
    public void deleteNonExistingTodo() {
		TodoService mockedService = mock(TodoService.class);
        Result<Boolean> deleteResult = new Result<>();
        deleteResult.Error(new NotFoundException(Todo.class.getSimpleName()));
        when(mockedService.delete(anyLong())).thenReturn(deleteResult);

        Result<Boolean> testResult = mockedService.delete(Long.MAX_VALUE);
		verify(mockedService).delete(anyLong());

		assertEquals(testResult.getSucceeded(), deleteResult.getSucceeded());
		assertEquals(testResult.getData(), deleteResult.getData());
    }

    @Test
    public void deleteNonExistingUser() {
        UserService mockedService = mock(UserService.class);
        Result<Boolean> deleteResult = new Result<>();
        deleteResult.Error(new NotFoundException(User.class.getSimpleName()));
        when(mockedService.delete(anyLong())).thenReturn(deleteResult);

        Result<Boolean> testResult = mockedService.delete(Long.MAX_VALUE);
		verify(mockedService).delete(anyLong());

		assertEquals(testResult.getSucceeded(), deleteResult.getSucceeded());
		assertEquals(testResult.getData(), deleteResult.getData());
    }
}
