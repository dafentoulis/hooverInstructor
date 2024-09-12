package com.hoover.hoover_instructor.domain;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class CleaningResult {

    private final Coords coords;

    private final int patches;
}
