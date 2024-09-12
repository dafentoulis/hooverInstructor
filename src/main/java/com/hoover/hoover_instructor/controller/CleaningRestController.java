package com.hoover.hoover_instructor.controller;


import com.hoover.hoover_instructor.dto.CleaningRequestDto;
import com.hoover.hoover_instructor.dto.CleaningResultDto;
import com.hoover.hoover_instructor.exceptions.MyException;
import com.hoover.hoover_instructor.service.CleaningInterface;
import com.hoover.hoover_instructor.mappers.CleaningRestControllerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cleaning operations")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CleaningRestController {
      private final CleaningInterface cleaningUseCase;
      private final CleaningRestControllerMapper cleaningRestControllerMapper;

    @Operation(summary = "Request hoover cleaning", description = "Cleans the area using the given instructions")
    @ApiResponse(responseCode = "200", content = @Content(examples = {
            @ExampleObject(value = """
                            {"coords":[1,3],"patches":1}
                            """)
    }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    @PostMapping("/operations/cleaning")
    public CleaningResultDto requestCleaning(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(value = """
                            {"roomSize":[5,5],"coords":[1,2],"patches":[[1,0],[2,2],[2,3]],"instructions":"NNESEESWNWW"}
                                    """)}
                    ))
            @RequestBody @Valid CleaningRequestDto cleaningRequestDto) {
        validateInput(cleaningRequestDto);
        return cleaningRestControllerMapper.fromDomain
                (cleaningUseCase.startCleaning(cleaningRestControllerMapper.toDomain(cleaningRequestDto)));
    }

    private void validateInput(CleaningRequestDto cleaningRequestDto) {
        if(cleaningRequestDto.getRoomSize()[0] < 0 || cleaningRequestDto.getRoomSize()[1] < 0){
            throw new MyException("Room size cannot be negative", HttpStatus.BAD_REQUEST);
        }
        if(cleaningRequestDto.getRoomSize()[0] != cleaningRequestDto.getRoomSize()[1]){
            throw new MyException("Room must be a rectangle", HttpStatus.BAD_REQUEST);
        }
        if(cleaningRequestDto.getCoords()[0] < 0 || cleaningRequestDto.getCoords()[1] < 0){
            throw new MyException("Coords cannot be negative", HttpStatus.BAD_REQUEST);
        }
        if(cleaningRequestDto.getCoords()[0] > cleaningRequestDto.getRoomSize()[0] || cleaningRequestDto.getCoords()[1] > cleaningRequestDto.getRoomSize()[1]){
            throw new MyException("Coords cannot be greater than the room size", HttpStatus.BAD_REQUEST);
        }
        if(cleaningRequestDto.getPatches().length < 1){
            throw new MyException("Patches cannot be empty", HttpStatus.BAD_REQUEST);
        }
        for(int[] patch: cleaningRequestDto.getPatches()){
            if (patch[0] < 0 || patch[1] < 0) {
                throw new MyException("Patches cannot be negative", HttpStatus.BAD_REQUEST);
            }
            if (patch[0] > cleaningRequestDto.getRoomSize()[0] || patch[1] > cleaningRequestDto.getRoomSize()[1]) {
                throw new MyException("Patches must be located inside the room", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
