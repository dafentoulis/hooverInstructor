package com.hoover.hoover_instructor.domain;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class CleaningRequest {

    private final Coords roomSize;

    private final Coords coords;

    private final List<Coords> patches;

    private final String instructions;
}
