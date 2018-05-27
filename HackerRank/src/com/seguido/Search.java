package com.seguido;

import com.sun.istack.internal.NotNull;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Search {

    static int findMedian(@NotNull  int[] arr) {
        boolean even = arr.length %2 ==0;
        double median;
        Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1> o2){
                    return 1;
                }else if (o1< o2){
                    return -1;
                }else {
                    return 0;
                }
            }

        });
        for (int i: arr){
            queue.add(Integer.valueOf(i));
        }
        for (int i = 0; i < arr.length ; i++){
            arr[i]= queue.poll();
        }

        if (even){
            median = (arr[(int)(arr.length/2)]+arr[(int)(arr.length/2)-1])/2;
        } else {
            median = arr[(int)(arr.length/2)];
        }

        return (int)median;
    }
}
