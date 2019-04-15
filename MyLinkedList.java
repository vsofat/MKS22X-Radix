import java.util.*;
import java.io.*;

public class MyLinkedList<E> {
 private int size;
 private Node start,end,current;

 public MyLinkedList(Node first) {
   start = first;
   end = first;
   size = 1;
   current = first;
  }

  public MyLinkedList(E value) {
    Node firstNode = new Node(value);
    start = firstNode;
    end = firstNode;
    current = firstNode;
  }

  public MyLinkedList() {
  }

  public int size() {
   return size;
  }


  public boolean add(E value) {

    Node toAdd = new Node(value);
    if (start == null && end == null) {
      start = toAdd;
      current = start;
    }
    else {
      toAdd.setPrev(end);
      end.setNext(toAdd);
    }
    end = toAdd;
    size ++;
    return true;
  }

  public String toString(){
    if (size() == 0) {
      return "[]";
    }
    Node current = start;
    String output = "[";
    while (current.hasNext()) {
      output += current.getData();
      output += ", ";
      current = current.next();
    }
    output += end.getData();
    return output += "]";
  }

  private Node getNthNode(int n) {
    if (n >= size() || n < 0) {
      throw new IndexOutOfBoundsException("Index " + n + " is out of bounds.");
    }
    Node result = start;
    int index = 1;
    while (index <= n) {
      result = result.next();
      index ++;
    }
    return result;
  }

public void add(int index,E value) {
  if (index > size() || index < 0) {
    throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
  }
  Node toAdd = new Node(value);
  if (index == 0) {
    if (size > 0) {
      Node atIndex = getNthNode(index);
      start = toAdd;
      start.setNext(atIndex);
    }
    else {
      start = toAdd;
      end = toAdd;
    }
  }
  else if (index == size()) { // adding to last index.
    add(value);

  }
  else {
    Node atIndex = getNthNode(index);
    Node previous = atIndex.prev();
    previous.setNext(toAdd);
    toAdd.setNext(atIndex);
    toAdd.setPrev(previous);
    atIndex.setPrev(toAdd);
  }
  size ++;
}

//@SuppressWarnings("unchecked")
public E remove(int index) {
  if (index >= size() || index < 0) {
    throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
  }
  Node current = getNthNode(index);
  if (index == 0) {
    if (size() == 1) {
      start = null;
    } else {
    start = getNthNode(1);
    start.setPrev(null);
  }
  } else if (index + 1 == size()) {
    end = getNthNode(index-1);
    end.setNext(null);
  } else {
    Node prevNode = current.prev();
    Node nextNode = current.next();
    prevNode.setNext(nextNode);
    nextNode.setPrev(prevNode);
  }
  size --;
  return (E)current.getData();
}

public void extend(MyLinkedList other){
    if (other.size() > 0) {
      Node otherFirst = other.getNthNode(0);
      if (size() > 0) {
        end.setNext(otherFirst);
      } else {
        start = otherFirst;
      }
      end = other.end;
      size += other.size();
    }
    other.size = 0;
    other.start = null;
    other.end = null;
}

  public E getNext() {
    E out = (E) current.getData();
    if (current == end) current = start;
    else current = current.next();
    return out;
  }

  public void resetCur() {
    current = start;
  }

}
