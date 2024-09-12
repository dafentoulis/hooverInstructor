package com.hoover.hoover_instructor.controller;

import com.hoover.hoover_instructor.service.CleaningService;
import com.hoover.hoover_instructor.domain.CleaningRequest;
import com.hoover.hoover_instructor.domain.CleaningResult;
import com.hoover.hoover_instructor.domain.Coords;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class CleaningServiceTest {

    @Autowired
    CleaningService cleaningService;
    static List<CleaningRequest> cleaningRequests;
    static List<CleaningResult> cleaningResults;

    @BeforeAll
    public static void setup(){
        List<Coords> patches = new ArrayList<>();
        cleaningRequests = new ArrayList<>();
        cleaningResults = new ArrayList<>();

        // 1st request
        patches.add((Coords.builder().x(1).y(0).build()));
        patches.add(Coords.builder().x(2).y(2).build());
        patches.add(Coords.builder().x(2).y(3).build());

        CleaningResult cleaningResultExpected1 = CleaningResult.builder().coords(Coords.builder().x(1).y(3).build()).patches(1).build();
        CleaningRequest cleaningRequest1 = CleaningRequest.builder()
                .roomSize(Coords.builder().x(5).y(5).build())
                .coords(Coords.builder().x(1).y(2).build())
                .patches(patches)
                .instructions("NNESEESWNWW").build();

        // 2nd request
        patches = new ArrayList<>();
        patches.add((Coords.builder().x(2).y(3).build()));
        patches.add(Coords.builder().x(4).y(2).build());
        patches.add(Coords.builder().x(4).y(5).build());
        patches.add(Coords.builder().x(6).y(3).build());

        CleaningResult cleaningResultExpected2 = CleaningResult.builder().coords(Coords.builder().x(5).y(1).build()).patches(2).build();
        CleaningRequest cleaningRequest2 = CleaningRequest.builder()
                .roomSize(Coords.builder().x(6).y(6).build())
                .coords(Coords.builder().x(1).y(1).build())
                .patches(patches)
                .instructions("SSNNNNNNNESSSEEEEESSW").build();

        // 3rd request
        patches = new ArrayList<>();
        patches.add((Coords.builder().x(1).y(1).build()));

        CleaningResult cleaningResultExpected3 = CleaningResult.builder().coords(Coords.builder().x(1).y(0).build()).patches(1).build();
        CleaningRequest cleaningRequest3 = CleaningRequest.builder()
                .roomSize(Coords.builder().x(2).y(2).build())
                .coords(Coords.builder().x(0).y(0).build())
                .patches(patches)
                .instructions("SNNEESWS").build();

        // 4th request
        patches = new ArrayList<>();
        patches.add((Coords.builder().x(1).y(1).build()));
        patches.add(Coords.builder().x(2).y(2).build());
        patches.add(Coords.builder().x(3).y(3).build());
        patches.add((Coords.builder().x(6).y(1).build()));
        patches.add(Coords.builder().x(5).y(2).build());
        patches.add(Coords.builder().x(3).y(4).build());

        CleaningResult cleaningResultExpected4 = CleaningResult.builder().coords(Coords.builder().x(3).y(3).build()).patches(2).build();
        CleaningRequest cleaningRequest4 = CleaningRequest.builder()
                .roomSize(Coords.builder().x(8).y(8).build())
                .coords(Coords.builder().x(3).y(3).build())
                .patches(patches)
                .instructions("NNESEESWNWWS").build();

        cleaningRequests.add(cleaningRequest1);
        cleaningRequests.add(cleaningRequest2);
        cleaningRequests.add(cleaningRequest3);
        cleaningRequests.add(cleaningRequest4);
        cleaningResults.add(cleaningResultExpected1);
        cleaningResults.add(cleaningResultExpected2);
        cleaningResults.add(cleaningResultExpected3);
        cleaningResults.add(cleaningResultExpected4);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void cleaningTest(int cleaningTestOrder) {
        CleaningResult cleaningResult = cleaningService.startCleaning(cleaningRequests.get(cleaningTestOrder));

        Assertions.assertThat(cleaningResult.getCoords()).isEqualTo(cleaningResults.get(cleaningTestOrder).getCoords());
        Assertions.assertThat(cleaningResult.getPatches()).isEqualTo(cleaningResults.get(cleaningTestOrder).getPatches());
    }
}
