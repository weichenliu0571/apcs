/*
Mister George :: Diana Akhmedova, Ziying Jian, Weichen Liu
APCS pd08
HW78 - Double up
2022-03-16w
time spent : 0.8 hrs

DISCO
- We need more instance variables for a doubly linked list: _prevNode.
- We need more accessor methods for a doubly linked list: getPrev(), setPrev().

QCC
- In what conditions is a doubly linked list most efficient?

ALGO ADD
- Special case: We add at index 0, so we just run normal add().
- Create prevNode, which is a the node to the left of the index we are adding.
- Create nextNode, which is the node to the right of the index we are adding.
- Create a newNode with _prevNode = prevNode and _nextNode = nextNode. 
- Set the _nextNode of prevNode to be newNode and set the _prevNode of nextNode to be newNode. 
- Increment _size. 
- Return true if operations completed. 

ALGO REM
- Special case: We remove at index 0, so we just _head to be _head.getNext().
- Create prevNode, which is the node to the left of the index we are removing.
- Create nextNode, which is currently the node we are removing.
- Store the cargo of that node in a String variable.
- Move nextNode to the nextNode.
- Set the _nextNode of prevNode to be nextNode. 
- Set the _prevNode of nextNode to be prevNode. 
- Decrement size.
- Return removed value. 
*/


public class LList implements List //interface def must be in this dir
{

  //instance vars
  private DLLNode _head;
  private int _size;

  // constructor -- initializes instance vars
  public LList( )
  {
    _head = null; //at birth, a list has no elements
    _size = 0; 

  }


  //--------------v  List interface methods  v--------------

  public boolean add( String newVal )
  {
    DLLNode temp = new DLLNode(newVal, null, _head);
    _head = temp;
    _size++;

    return true;
  }

  public void add (int index, String newVal) {
      
    if ( index < 0 || index > size() ) {
        throw new IndexOutOfBoundsException();
    }
    
    if (index == 0) {
        add(newVal);
        return;
    }

    DLLNode prevNode = getNode(index - 1);
    DLLNode nextNode = prevNode.getNext();
    DLLNode newNode = new DLLNode(newVal, prevNode, nextNode );

    prevNode.setNext(newNode);
    nextNode.setPrev(newNode);
    _size++;

  }

  public String remove (int index) {
    if ( index < 0 || index >= size() ) {
        throw new IndexOutOfBoundsException();
    }

    String removedNode;
    if (index == 0){
	    removedNode = _head.getCargo();
	    _head = _head.getNext();
      _head.setPrev(null);
	    _size--;
	    return removedNode;
    }

    DLLNode prevNode = getNode(index - 1);
    DLLNode nextNode = prevNode.getNext();
    removedNode = nextNode.getCargo();

    nextNode = nextNode.getNext();
    prevNode.setNext(nextNode);

    if (index != size() - 1) {
        nextNode.setPrev(prevNode);
    }

    _size--;
    return removedNode;
  }

  public String get( int index )
  {
    if ( index < 0 || index >= size() ) {
        throw new IndexOutOfBoundsException();
    }

    DLLNode temp = getNode(index);

    return temp.getCargo();
  }


  public String set( int index, String newVal )
  {

    if ( index < 0 || index >= size() ) {
        throw new IndexOutOfBoundsException();
    }

    DLLNode temp = getNode(index);
  
    return temp.setCargo(newVal);
  }

  public DLLNode getNode( int index ) {

    DLLNode temp = _head;
    for (int i = 0; i < index; i++) {
        temp = temp.getNext();
    }

    return temp;
  }

  //return number of nodes in list
  public int size() { return _size; }

  //--------------^  List interface methods  ^--------------


  // override inherited toString
  public String toString()
  {
    String retStr = "HEAD->";
    DLLNode tmp = _head; //init tr
    while( tmp != null ) {
	    retStr += tmp.getCargo() + "->";
	    tmp = tmp.getNext();
    }
    retStr += "NULL";
    return retStr;
  }

}//end class LList
