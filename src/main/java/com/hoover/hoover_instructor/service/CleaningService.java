package com.hoover.hoover_instructor.service;

import com.hoover.hoover_instructor.domain.CleaningRequest;
import com.hoover.hoover_instructor.domain.CleaningResult;
import com.hoover.hoover_instructor.domain.Coords;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CleaningService implements CleaningInterface {
    @Override
    public CleaningResult startCleaning(CleaningRequest cleaningRequest) {
        log.info("Started cleaning process");
        Coords roomSize = cleaningRequest.getRoomSize();
        List<Coords> patchesToClean = cleaningRequest.getPatches();
        Coords initialCoords = Coords.builder().x(cleaningRequest.getCoords().getX()).y(cleaningRequest.getCoords().getY()).build();
        log.info("Initial hoover position: "+"["+initialCoords.getX()+","+initialCoords.getY()+"]");
        Coords currentCoords = Coords.builder().build();
        int cleanedPatches = 0;
        String[] instructionsArray = cleaningRequest.getInstructions().split("");

        cleanedPatches = findAndClean(patchesToClean, initialCoords, cleanedPatches);
        for(String command: instructionsArray){
            currentCoords = initialCoords;
            switch (command) {
                case "N":
                    log.info("Moving North to: "+"["+currentCoords.getX()+","+(currentCoords.getY()+1)+"]");
                    currentCoords.setY(confirmMovement(currentCoords.getY(),roomSize.getY(),1));
                    break;
                case "S":
                    log.info("Moving South to: "+"["+currentCoords.getX()+","+(currentCoords.getY()-1)+"]");
                    currentCoords.setY(confirmMovement(currentCoords.getY(),roomSize.getY(), -1));
                    break;
                case "W":
                    log.info("Moving West to: "+"["+(currentCoords.getX()-1)+","+currentCoords.getY()+"]");
                    currentCoords.setX(confirmMovement(currentCoords.getX(),roomSize.getX(), -1));
                    break;
                case "E":
                    log.info("Moving East to: "+"["+(currentCoords.getX()+1)+","+currentCoords.getY()+"]");
                    currentCoords.setX(confirmMovement(currentCoords.getX(),roomSize.getX(), +1));
                    break;
                default:
                    // do nothing
            }
            cleanedPatches = findAndClean(patchesToClean, currentCoords, cleanedPatches);
        }
        log.info("Cleaning finished. Patches cleaned: "+cleanedPatches);
        log.info("Hoover final position: "+currentCoords.getX()+","+currentCoords.getY());
        return CleaningResult.builder()
                .coords(currentCoords)
                .patches(cleanedPatches).build();
    }

    private static int findAndClean(List<Coords> patchesToClean, Coords coords, int cleanedPatches) {
        if(patchesToClean.contains(coords)){
            cleanedPatches++;
            patchesToClean.remove(coords);
            log.info("Found and cleaned dirt patch at "+"["+coords.getX()+","+coords.getY()+"]");
        }
        return cleanedPatches;
    }

    private int confirmMovement(int currentPosition, int roomSize, int direction) {
        if(currentPosition + direction > roomSize || currentPosition + direction < 0) {
            log.info("Wall identified, movement aborted, acquiring new instruction..");
            return currentPosition;
        }
        else{
            return currentPosition+direction;
        }
    }
}
