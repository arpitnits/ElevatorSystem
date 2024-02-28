package org.example.services;

import org.example.enums.Direction;
import org.example.pojos.Elevator;
import org.example.pojos.ElevatorRequest;

import static org.example.enums.Direction.*;
import static org.example.enums.RequestSource.OUTSIDE;

public class ElevatorService {

    private Elevator elevator;
    public ElevatorService(Elevator elevator) {
        this.elevator = elevator;
    }

    public void sendUpRequest(ElevatorRequest request) {
        if(OUTSIDE.equals(request.getRequestSource())) {
            //stop at source floor
            elevator.getUpRequests().add(request.getSourceFloor());
            System.out.println("Appending up requests to floor " + request.getSourceFloor());
        }
        elevator.getUpRequests().add(request.getDestFloor());
        System.out.println("Appending up requests to floor " + request.getDestFloor());
    }

    public void sendDownRequest(ElevatorRequest request) {
        if(OUTSIDE.equals(request.getRequestSource())) {
            //stop at source floor
            elevator.getDownRequests().add(request.getSourceFloor());
            System.out.println("Appending down requests to floor " + request.getSourceFloor());
        }
        elevator.getDownRequests().add(request.getDestFloor());
        System.out.println("Appending down requests to floor " + request.getDestFloor());
    }

    public void run() throws InterruptedException {
        while(true) {
            if(elevator.getDownRequests().isEmpty() && elevator.getUpRequests().isEmpty())
                Thread.sleep(2000);

            while(!elevator.getUpRequests().isEmpty()  || !elevator.getDownRequests().isEmpty()) {
                System.out.println("Running simulation");
                processRequests();
                System.out.println("Finished simulation");
            }

            elevator.setDirection(IDLE);
        }
    }

    public void processRequests() {
        if(UP.equals(elevator.getDirection()) || IDLE.equals(elevator.getDirection())) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }
    }

    public void processUpRequest() {
        while(!elevator.getUpRequests().isEmpty()) {
            int desiredFloor = elevator.getUpRequests().poll();
            elevator.setCurrentFloor(desiredFloor);
            System.out.println("Elevator going Up reached at floor " + desiredFloor);
        }
        if(!elevator.getDownRequests().isEmpty()) {
            elevator.setDirection(DOWN);
        } else {
            elevator.setDirection(IDLE);
        }
    }

    public void processDownRequest() {
        while(!elevator.getDownRequests().isEmpty()) {
            int desiredFloor = elevator.getDownRequests().poll();
            elevator.setCurrentFloor(desiredFloor);
            System.out.println("Elevator going Down reached at floor " + desiredFloor);
        }
        if(!elevator.getUpRequests().isEmpty()) {
            elevator.setDirection(UP);
        } else {
            elevator.setDirection(IDLE);
        }
    }
}
