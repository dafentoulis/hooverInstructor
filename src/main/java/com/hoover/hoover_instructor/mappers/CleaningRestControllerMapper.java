package com.hoover.hoover_instructor.mappers;

import com.hoover.hoover_instructor.dto.CleaningRequestDto;
import com.hoover.hoover_instructor.dto.CleaningResultDto;
import com.hoover.hoover_instructor.domain.CleaningRequest;
import com.hoover.hoover_instructor.domain.CleaningResult;
import com.hoover.hoover_instructor.domain.Coords;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CleaningRestControllerMapper {

    @Mapping(target = "coords", source = "domain.coords", qualifiedByName = "mapToArray")
    CleaningResultDto fromDomain(CleaningResult domain);

    @Mapping(target = "roomSize", source = "dto.roomSize", qualifiedByName = "mapToCoords")
    @Mapping(target = "coords", source = "dto.coords", qualifiedByName = "mapToCoords")
    @Mapping(target = "patches", source = "dto.patches", qualifiedByName = "mapToPatches")
    CleaningRequest toDomain(CleaningRequestDto dto);


    @Named("mapToCoords")
    default Coords mapToCoords(int[] coords) {
        return Coords.builder()
                .x(coords[0])
                .y(coords[1]).build();
    }

    @Named("mapToPatches")
    default List<Coords> mapToPatches(int[][] patchesCoords) {
        List<Coords> listOfPatchesCoords = new ArrayList<>();
        for(int[] coords: patchesCoords){
            listOfPatchesCoords.add(Coords.builder().x(coords[0]).y(coords[1]).build());
        }
        return listOfPatchesCoords;
    }

    @Named("mapToArray")
    default int[] mapToArray(Coords coords) {
        return new int[]{coords.getX(),coords.getY()};
    }


}
