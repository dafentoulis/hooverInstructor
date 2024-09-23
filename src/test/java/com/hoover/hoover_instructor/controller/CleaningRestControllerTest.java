package com.hoover.hoover_instructor.controller;

import com.hoover.hoover_instructor.domain.CleaningResult;
import com.hoover.hoover_instructor.domain.Coords;
import com.hoover.hoover_instructor.service.CleaningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CleaningRestControllerTest {

    @MockBean
    CleaningService cleaningService;
    CleaningResult cleaningResult;
    String cleaningRequestJson;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        cleaningResult = CleaningResult.builder().coords(Coords.builder().x(1).y(3).build()).patches(1).build();

        cleaningRequestJson = """
                {
                "roomSize" : [5, 5],
                "coords" : [1, 2],
                "patches" : [
                [1, 0],
                [2, 2],
                [2, 3]
                ],
                "instructions" : "NNESEESWNWW"
                }""";
    }

    @Test
    void cleaningTest() throws Exception{

        given(cleaningService.startCleaning(any()))
                .willReturn(cleaningResult);

        mockMvc.perform(post("/operations/cleaning")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cleaningRequestJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.coords[0]",
                        is(cleaningResult.getCoords().getX())))
                .andExpect(jsonPath("$.coords[1]",
                        is(cleaningResult.getCoords().getY())))
                .andExpect(jsonPath("$.patches",
                        is(cleaningResult.getPatches())));
    }
}
