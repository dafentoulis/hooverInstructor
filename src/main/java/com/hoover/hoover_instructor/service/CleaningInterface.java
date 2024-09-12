package com.hoover.hoover_instructor.service;

import com.hoover.hoover_instructor.domain.CleaningRequest;
import com.hoover.hoover_instructor.domain.CleaningResult;

public interface CleaningInterface {
    CleaningResult startCleaning(CleaningRequest cleaningRequest);

}
