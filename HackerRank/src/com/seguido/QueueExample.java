package com.seguido;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {
    Queue<String> queue = new PriorityQueue<String>(new Comparator<String>() {

        @Override
        public int compare(String o1, String o2) {
            if(o1.length() > o2.length()){
                return 1;
            }else if (o1.length() < o2.length()){
                return -1;
            }else {
                for(int i = 0; i< o1.length(); i++){
                    if(o1.charAt(i)> o2.charAt(i)){
                        return 1;
                    }else if (o1.charAt(i)<o2.charAt(i)){
                        return -1;
                    }
                }
                return 0;
            }
        }
    });
}
