package com.maveric.userservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.userservice.dto.ErrorDto;
import com.maveric.userservice.exceptionhandler.ExceptionControllerAdvisor;
import com.maveric.userservice.exceptionhandler.UserNotExist;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.maveric.userservice.UserServiceApplicationTests.APIV1;
import static com.maveric.userservice.UserServiceApplicationTests.getUserDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes= ExceptionControllerAdvisor.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ExceptionControllerAdvisor.class)
public class ExceptionControllerAdvisorTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;
    private final ExceptionControllerAdvisor controllerAdvisor = new ExceptionControllerAdvisor();

    @Test
    public void handleUserNotFoundException() {
        UserNotExist exception = new UserNotExist("User Not found");
        ErrorDto error = controllerAdvisor.handleUserNotFoundException(exception);
        assertEquals("404",error.getCode());
    }


    @Test
    public void whenRequestSyntaxNotValidShouldGetError404WhenRequestMadeToCreateUserDetails() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.post(APIV1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getUserDto()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void whenUserIdNotFoundShouldGetError404WhenRequestMadeToGetUserDetails() throws Exception
    {
        MvcResult mvcResult =
                mvc.perform(get(APIV1+"/userId1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();

        int expectedErrorResponse = 404;
        int actualResponse = mvcResult.getResponse().getStatus();
        System.out.println("actualResponseBody------->"+actualResponse);
        assertThat(actualResponse)
                .isEqualTo(expectedErrorResponse);
    }

    @Test
    public void whenUserIdNotFoundShouldGetError404WhenRequestMadeToDeleteUserDetails() throws Exception
    {
        MvcResult mvcResult =
                mvc.perform(delete(APIV1+"/userId1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();

        int expectedErrorResponse = 404;
        int actualResponse = mvcResult.getResponse().getStatus();
        System.out.println("actualResponseBody------->"+actualResponse);
        assertThat(actualResponse)
                .isEqualTo(expectedErrorResponse);
    }
    @Test
    public void whenUserIdNotFoundShouldGetError404WhenRequestMadeToUpdateUserDetails() throws Exception
    {
        MvcResult mvcResult =
                mvc.perform(put(APIV1+"/userId1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();

        int expectedErrorResponse = 404;
        int actualResponse = mvcResult.getResponse().getStatus();
        System.out.println("actualResponseBody------->"+actualResponse);
        assertThat(actualResponse)
                .isEqualTo(expectedErrorResponse);
    }

}
