import java.io.*;
import java.util.*;

public class Radix {

  public static void main(String[] args) {
    int[] data = {5,1,3,4,5,2,5,2,3,3,1,9};
    radixsort(data);
    System.out.println(Arrays.toString(data));
  }

  private static int getNthDigit(int len, int n) {
    String s = "" + Math.abs(len);

    //base
    //no zeroes
    if (s.length() < n){
      return 0;
    }

    int zeroes = (n - s.length()) + 1; // numbers of zeroes

    String strZero = "";

    for (int ind = 0; ind < zeroes; ind++) {
      strZero += "0";
    }

    strZero += s;

    int digit = strZero.length() - n - 1;
    return Integer.parseInt("" + strZero.charAt(digit));
  }

  public static void radixsort(int[] data){

    int passes = maxDigits(data);

    MyLinkedList<Integer>[] digits = new MyLinkedList[10];

    for (int bucket = 0; bucket < 10; bucket++) {
      digits[bucket] = new MyLinkedList<Integer>();
    }

    for (int index = 0; index < data.length; index++) {
      int element = data[index];
      int digit = getNthDigit(element,0); // get the Nth digit of the data you are at
      if (element > 0){
        digits[digit].add(element); // positive - add to end
      }
      else{
        digits[digit].add(0,element); // negative - add to beg
      }
    }

    MyLinkedList<Integer> result = new MyLinkedList<Integer>();

    for (int index = 0; index < 10; index++) {
      if (!digits[index].equals(null)){
        result.extend(digits[index]);
      }

    }

    radix(result,1,passes,data);
  }

  private static void radix(MyLinkedList<Integer> data, int num, int passes, int[] dataArr) {

    if (num == passes) {
      data.resetCur();
      for (int index = 0; index<data.size(); index++) {
        dataArr[index] = data.getNext();
      }
      return; // do nothing
    }

    MyLinkedList<Integer>[] digits = new MyLinkedList[10]; // should have all bucks

    for (int index = 0; index < 10; index++) {
      digits[index] = new MyLinkedList<Integer>();
    }

    data.resetCur();

    for (int index = 0; index < data.size(); index ++) {

      int element = data.getNext();

      int digit = getNthDigit(element,num); // find the digit

      if (element > 0){
        digits[digit].add(element); // positive - back
      }
      else{
        digits[digit].add(0,element); // negative - beginning
      }
    }

    MyLinkedList<Integer> out = new MyLinkedList<Integer>(); // readd data to this list

    if (num + 1 == passes) {

      if (! digits[0].equals(null)){
        out.extend(digits[0]);
      }
      for (int index = 1; index < 10; index++) {
        while (digits[index].size() > 0) {
          int element = digits[index].remove(0);
          if (element > 0){
            out.add(element);
          }
          else{
            out.add(0,element);
          }

        }
      }
    }

    else {

      for (int index = 0; index < 10; index++) { // links list
        if (!digits[index].equals(null)){
          out.extend(digits[index]);
        }
      }

      radix(out,num+1,passes,dataArr);
    }
  }

    private static int maxDigits(int[] data) {
      int max = 0;
      for (int num : data) {
        if (Math.abs(num) > max) max = Math.abs(num);
      }
      String answer = "" + max;
      return answer.length();
    }

  }
