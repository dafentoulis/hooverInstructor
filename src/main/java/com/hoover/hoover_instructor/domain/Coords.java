package com.hoover.hoover_instructor.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class Coords {
    int x;
    int y;

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        // Check if o is an instance of Coords or not
        if (!(o instanceof Coords c)) {
            return false;
        }


        return x == c.x
                && y == c.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x,y);
    }
}
