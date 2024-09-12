package com.hoover.hoover_instructor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
public class CleaningRequestDto {

    @NotNull(message = "roomSize is required.")
    private final int[] roomSize;

    @NotNull(message = "coords is required.")
    private final int[] coords;

    @NotNull(message = "patches are required.")
    private final int[][] patches;

    @NotBlank(message = "instructions are required.")
    @Pattern(regexp = "([NSEW])*", message = "Valid instructions are N, S, E, W")
    private final String instructions;
}
