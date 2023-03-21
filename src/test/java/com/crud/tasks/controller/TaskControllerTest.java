package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getTasks() throws Exception {
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(132L, "title", "content"));
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtoList);

        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(132)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$.[0].content", is("content")));
    }

    @Test
    public void getTask() throws Exception {
        when(taskMapper.mapToTaskDto(any())).thenReturn(new TaskDto(1L, "title1", "content1"));
        when(dbService.getTaskById(any())).thenReturn(ofNullable(new Task(1L, "title1", "content1")));

        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON)
        .param("taskId", "2"))
        .andExpect(status().isOk())
        .andDo(print());
    }

    @Test
    public void deleteTask() throws Exception{
        mockMvc.perform(delete("/v1/task/deleteTask").contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateTask() throws Exception {
        Gson gson = new Gson();
        String jsonContent = gson.toJson(new TaskDto(1L, "title", "content"));

        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createTask() throws Exception {
        Gson gson = new Gson();
        String jsonContent = gson.toJson(new TaskDto(1L, "title", "content"));

        Task task = new Task(1L, "title", "content");

        when(dbService.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenReturn(task);

        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }
}