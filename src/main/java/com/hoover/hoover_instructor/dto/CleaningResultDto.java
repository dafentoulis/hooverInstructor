package com.hoover.hoover_instructor.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CleaningResultDto {
    private final int[] coords;
    private final int patches;
}
