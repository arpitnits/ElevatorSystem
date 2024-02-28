package org.example;

import org.example.enums.Direction;
import org.example.enums.RequestSource;
import org.example.pojos.Elevator;
import org.example.pojos.ElevatorRequest;
import org.example.services.ElevatorService;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.CompletableFuture;

import static org.example.enums.Direction.DOWN;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("Hello world!");

        Elevator elevator = new Elevator(3, Direction.IDLE, new PriorityQueue<>(), new PriorityQueue<>(Comparator.reverseOrder()));


        ElevatorService elevatorService = new ElevatorService(elevator);

        // Two people inside of the elevator pressed button to go up to floor 5 and 3.
        elevatorService.sendUpRequest(new ElevatorRequest(elevator.getCurrentFloor(), 7, Direction.UP, RequestSource.INSIDE));
        elevatorService.sendUpRequest(new ElevatorRequest(elevator.getCurrentFloor(), 5, Direction.UP, RequestSource.INSIDE));

        CompletableFuture.runAsync(() -> {
            try {
                elevatorService.run();
            } catch (InterruptedException e) {
                System.out.println("Unable to complete");
            }
        });

        Thread.sleep(1000);
        elevator.setDirection(DOWN);
        // One person outside of the elevator at floor 4 pressed button to go down to floor 0
        elevatorService.sendDownRequest(new ElevatorRequest(4, 0, DOWN, RequestSource.OUTSIDE));

        elevatorService.sendUpRequest(new ElevatorRequest(elevator.getCurrentFloor(), 1, Direction.UP, RequestSource.INSIDE));

        // Two person inside of the elevator pressed button to go down to floor 1 and 2.
        elevatorService.sendDownRequest(new ElevatorRequest(elevator.getCurrentFloor(), 1, DOWN, RequestSource.INSIDE));
        elevatorService.sendDownRequest(new ElevatorRequest(elevator.getCurrentFloor(), 2, DOWN, RequestSource.INSIDE));

        Thread.sleep(10000);
    }
}