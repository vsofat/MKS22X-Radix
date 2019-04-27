 import java.io.*;
import java.util.*;

public class Radix {

  public static void main(String[] args) {
  int[] data = {5,1,3,4,5,2,5,2,3,3,1,9};
  radixsort(data);
  System.out.println(Arrays.toString(data));
 }

  private static int getNthDigit(int num, int n) {
    String answer = "" + Math.abs(num);

    if (answer.length() < n){
      return 0;
    }

    int zeroes = (n - answer.length()) +1;
    String ans2 = "";

    for (int i=0;i<zeroes;i++) {
      ans2 += "0";
    }

    ans2 += answer;

    int index = ans2.length() - n - 1;
    return Integer.parseInt("" + ans2.charAt(index));
  }

  public static void radixsort(int[] data){
   int passes = maxDigits(data);

     MyLinkedList<Integer>[] digits = new MyLinkedList[10];

     for (int i2 = 0; i2 < 10; i2++) {
       digits[i2] = new MyLinkedList<Integer>();
     }

     for (int idx=0;idx<data.length;idx++) {
       int element = data[idx];

       int digit = getNthDigit(element,0);

       if (element > 0) digits[digit].add(element);

       else digits[digit].add(0,element);

     }

     MyLinkedList<Integer>  out = new MyLinkedList<Integer>();
     for (int idx=0;idx<10;idx++) {
       if (! digits[idx].equals(null)) out.extend(digits[idx]);
     }

     radix(out,1,passes,data);
 }

 private static void radix(MyLinkedList<Integer> data, int i, int passes, int[] dataa) {

   if (i==passes) {

     data.resetCur();
     for (int l=0;l<data.size();l++) {
       dataa[l] = data.getNext();
     }
     return;

    }

   MyLinkedList<Integer>[] digits = new MyLinkedList[10];

   for (int i2=0;i2<10;i2++) {
     digits[i2] = new MyLinkedList<Integer>();
   }

   data.resetCur();
   for (int idx=0;idx<data.size();idx++) {

     int element = data.getNext();

     int digit = getNthDigit(element,i);

     if (element > 0) digits[digit].add(element);

     else digits[digit].add(0,element);
   }

     MyLinkedList<Integer> out = new MyLinkedList<Integer>();

     if (i+ 1==passes) {

       if (! digits[0].equals(null)) out.extend(digits[0]);
       for (int idx=1;idx<10;idx++) {
           while (digits[idx].size() > 0) {
             int element = digits[idx].remove(0);
             if (element > 0) out.add(element);
             else out.add(0,element);
           }
       }
     } else {
       for (int idx=0;idx<10;idx++) {
         if (! digits[idx].equals(null)) out.extend(digits[idx]);
       }
     }

     radix(out,i+1,passes,dataa);

 }

  private static int maxDigits(int[] data) {
    int max = 0;
    for (int index: data) {
      if (Math.abs(index) > max){
        max = Math.abs(index);
      }
    }
    String stringMax = ""+max;
    return stringMax.length();
  }

}
