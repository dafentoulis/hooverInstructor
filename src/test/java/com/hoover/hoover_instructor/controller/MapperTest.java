package com.hoover.hoover_instructor.controller;

import com.hoover.hoover_instructor.dto.CleaningRequestDto;
import com.hoover.hoover_instructor.domain.CleaningRequest;
import com.hoover.hoover_instructor.domain.Coords;
import com.hoover.hoover_instructor.dto.CleaningResultDto;
import com.hoover.hoover_instructor.mappers.CleaningRestControllerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class MapperTest {
    CleaningRequestDto cleaningRequestDto;
    @Autowired
    CleaningRestControllerMapper cleaningRestControllerMapper;

    @BeforeEach
    public void setup(){

        cleaningRequestDto = CleaningRequestDto.builder()
                .roomSize(new int[]{5,5})
                .coords(new int[]{1,2})
                .patches(new int[][]{{1, 0}, {2,2}, {2,3}})
                .instructions("NNESEESWNWW").build();
    }

    @Test
    void mapperTest() {
        CleaningRequest cleaningRequest = cleaningRestControllerMapper.toDomain(cleaningRequestDto);
        assertThat( cleaningRequest.getRoomSize() ).isEqualTo( Coords.builder().x(5).y(5).build() );
        assertThat( cleaningRequest.getCoords() ).isEqualTo( Coords.builder().x(1).y(2).build() );
        assertThat( cleaningRequest.getPatches() ).isEqualTo(List.of(Coords.builder().x(1).y(0).build(),Coords.builder().x(2).y(2).build(),Coords.builder().x(2).y(3).build()));
        assertThat( cleaningRequest.getInstructions() ).isEqualTo("NNESEESWNWW");
    }
}
