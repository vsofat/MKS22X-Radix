import java.io.*;
import java.util.*;

public class Radix {

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

  private static void radix(MyLinkedList<Integer> data, int i, int passes, int[] newData) {

    if (i==passes) {
      data.resetCur();
      for (int l=0; l<data.size(); l++) {
        newData[l] = data.getNext();
      }
      return;
    }

    MyLinkedList<Integer>[] digits = new MyLinkedList[10];

    for (int i2=0; i2<10; i2++) {
      digits[i2] = new MyLinkedList<Integer>();
    }

    data.resetCur();
    for (int idx=0;idx<data.size();idx++) {

      int element = data.getNext();
      int digit = getNthDigit(element,i);

      if (element > 0){
        digits[digit].add(element);
      }

      else{
        digits[digit].add(0,element);
      }
    }
  }
  }
