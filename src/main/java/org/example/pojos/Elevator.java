package org.example.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enums.Direction;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Data
@AllArgsConstructor
public class Elevator {

    private int currentFloor;
    private Direction direction;

    //minHeap
    private Queue<Integer> upRequests;

    //maxHeap
    private Queue<Integer> downRequests;
}
