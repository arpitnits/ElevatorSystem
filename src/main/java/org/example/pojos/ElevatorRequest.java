package org.example.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enums.Direction;
import org.example.enums.RequestSource;

@Data
@AllArgsConstructor
public class ElevatorRequest {

    private int sourceFloor;
    private int destFloor;
    private Direction direction;

    private RequestSource requestSource;

}
