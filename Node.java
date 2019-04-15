public class Node<E> {
  private E data;
  private Node next,prev;

@SuppressWarnings("unchecked")
  public Node(E generic) { // should the next node be part of the constructor?
    data = generic;
  }

@SuppressWarnings("unchecked")
  public Node() {

  }

  public void setNext(Node setNext) {
    next = setNext;
  }

  public void setPrev(Node setPrev) {
    prev = setPrev;
  }

  public E getData() {
    return data;
  }

  public E setData(E newData) {
    E oldData = data;

    data = newData;
    return oldData;
  }

  public Node next() {
    return next;
  }

  public Node aoprev() {
    return prev;
  }

  public boolean hasNext() {
    if (next != null) {
      return true;
    } else {
      return false;
    }
  }

  public String toString() {
    return "" + getData();
  }

}
