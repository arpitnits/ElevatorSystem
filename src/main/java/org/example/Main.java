package org.example;

import org.example.enums.Direction;
import org.example.enums.RequestSource;
import org.example.pojos.Elevator;
import org.example.pojos.ElevatorRequest;
import org.example.services.ElevatorService;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        Elevator elevator = new Elevator(3, Direction.IDLE, new PriorityQueue<>(), new PriorityQueue<>(Comparator.reverseOrder()));

        ElevatorRequest upRequest1 = new ElevatorRequest(elevator.getCurrentFloor(), 5, Direction.UP, RequestSource.INSIDE);
        ElevatorRequest upRequest2 = new ElevatorRequest(elevator.getCurrentFloor(), 3, Direction.UP, RequestSource.INSIDE);

        ElevatorRequest downRequest1 = new ElevatorRequest(elevator.getCurrentFloor(), 1, Direction.DOWN, RequestSource.INSIDE);
        ElevatorRequest downRequest2 = new ElevatorRequest(elevator.getCurrentFloor(), 2, Direction.DOWN, RequestSource.INSIDE);
        ElevatorRequest downRequest3 = new ElevatorRequest(4, 0, Direction.DOWN, RequestSource.OUTSIDE);


        ElevatorService elevatorService = new ElevatorService(elevator);

        // Two people inside of the elevator pressed button to go up to floor 5 and 3.
        elevatorService.sendUpRequest(upRequest1);
        elevatorService.sendUpRequest(upRequest2);

        // One person outside of the elevator at floor 4 pressed button to go down to floor 0
        elevatorService.sendDownRequest(downRequest3);

        elevatorService.sendUpRequest(new ElevatorRequest(elevator.getCurrentFloor(), 7, Direction.UP, RequestSource.INSIDE));

        // Two person inside of the elevator pressed button to go down to floor 1 and 2.
        elevatorService.sendDownRequest(downRequest1);
        elevatorService.sendDownRequest(downRequest2);

        elevatorService.run();
    }
}