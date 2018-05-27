package com.seguido;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the insertionSort1 function below.
    static void insertionSort1(int n, int[] arr) {
        int valueToInsert = arr[n-1];
        int aux;
        for(int i = n-2; i>=0;i--){
            aux = arr[i+1];
            if (valueToInsert < arr[i]){
                arr[i+1] = arr[i];
                if(i==0){
                    arr[i]= valueToInsert;

                }
            }else if(valueToInsert >= arr[i]){
                arr[i+1] = valueToInsert;
            }

            if (aux != arr[i+1]){
                printArray(arr);
            }
            if (arr[i+1]==valueToInsert) {
                return;
            }
        }

    }

    static void printArray(int[] array){
        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        insertionSort1(n, arr);

        scanner.close();
    }
}

