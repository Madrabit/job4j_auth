package ru.job4j.auth.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.reset;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.auth.AuthApplication;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

@SpringBootTest(classes = AuthApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository repository;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldReturnFindPersonById() throws Exception {
        this.mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void whenPostThenCreate() throws Exception {
        Person alex = new Person("alex", "123");
        given(repository.save(Mockito.any())).willReturn(alex);

        mockMvc.perform(post("/person/").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex))).andExpect(status().isCreated()).andExpect(jsonPath("$.login", is("alex")));
        verify(repository, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(repository);
    }
}

