/*
 :: Diana Akhmedova, Ziying Jian, Weichen Liu
APCS pd08
HW72 - implementing an algorithm to find the yth smallest element in a list
2022-03-08t
time spent : 1.2 hrs

ALGO

BEST CASE SCENARIO
When the splitter is the yth smallest element in the list. 

WORST CASE SCENARIO
When the splitter and the yth smallest element are on opposite ends of the list. 

DISCO
When we return s in relativeSort(), we return the number of elements before the splitter. From this, we know that splitter is the (y+1)th smallest element.

QCC
How will we use fastSelect() to sort the list?

Include test cases in main() method illustrating each best- and worst-case scenario.
*/




public class FastSelect
{    
  //--------------v  HELPER METHODS  v--------------
  //swap values at indices x, y in array o
  public static void swap( int x, int y, int[] o )
  {
    int tmp = o[x];
    o[x] = o[y];
    o[y] = tmp;
  }

  //print input array 
  public static void printArr( int[] a )
  {
    for ( int o : a )
      System.out.print( o + " " );
    System.out.println();
  }

  //shuffle elements of input array
  public static void shuffle( int[] d )
  {
    int tmp;
    int swapPos;
    for( int i = 0; i < d.length; i++ ) {
      tmp = d[i];
      swapPos = i + (int)( (d.length - i) * Math.random() );
      swap( i, swapPos, d );
    }
  }
    
  //return int array of size s, with each element fr range [0,maxVal)
  public static int[] buildArray( int s, int maxVal )
  {
    int[] retArr = new int[s];
    for( int i = 0; i < retArr.length; i++ )
      retArr[i] = (int)( maxVal * Math.random() );
    return retArr;
  }
  //--------------^  HELPER METHODS  ^--------------


  /**
   * int relativeSort(int[],int,int,int)
   * DESCRIPTION
   * Array is partitioned into two subarrays with a split(element of index splitter).
   * Elements to the left of split is less than split.
   * Elements to the right of split is greater than split. 
   * Split is adjusted to where it should be. 
   *
   *
   * @param arr
   * @param a -- lo -- The beginning index we use for partitioning
   * @param b -- hi -- The end index we use for partitioning 
   * @param c -- splitter -- The index of element that we compare the rest of the elements of the list to. 
   * @return int 
   *
   */
  public static int relativeSort( int[] arr, int lo, int hi, int splitter)
  {
    int split = arr[splitter];
    
    swap(splitter, hi, arr); // split is moved to the end
    
    int s = lo;
    
    for (int i = lo; i < hi; i++){
      if (arr[i] < split){ // compares elements of the list to split
        swap(s, i, arr);
        s += 1;
      }
    }
    
    swap(hi, s, arr); // split put back into place
    
    return s; // returns # of elements before splitIndex
  }//end relativeSort

  public static int fastSelect(int[] arr, int lo, int hi, int splitter, int y) { // y is the (y + 1)th smallest element in the list
    if (relativeSort(arr, lo, hi, splitter) == y) {
      return arr[splitter];
    } else {
      if (relativeSort(arr, lo, hi, splitter) < y) {
        return fastSelect(arr, splitter + 1, hi, splitter + 1, y);
        } else {
          return fastSelect(arr, lo, splitter - 1, splitter - 1, y);
        }
      }
  }


  //main method for testing
  public static void main( String[] args )
  {

    //init test arrays of magic numbers
    int[] arr1 = {1,2,3,4,5};
    int[] arr3 = {1,28,33,4982,37};
    int[] arr4 = {5,4,17,9000,6};
    int[] arr5 = {5,4,3,2,1};

    // Best Case Scenario 
    // Splitter is on the target
    System.out.println(fastSelect(arr3, 0, 4, 0, 0));

    // Worst Case Scenario
    // Splitter is on opposite ends with the target
    System.out.println(fastSelect(arr5, 0, 4, 0, 0));

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // run relativeSort on each array,
    // holding a & b fixed, varying c...
    for( int testC = 0; testC < 5; testC++ ) {
    System.out.println("arr1: ");
    printArr(arr1);
    relativeSort(arr1,0,4,testC);
    System.out.println("after relativeSort w/ a=0,b=4,c=" 
    + testC +"...");
    printArr(arr1);
    System.out.println("-----------------------");

    System.out.println("arr3:");
    printArr(arr3);
    relativeSort(arr3,0,4,testC);
    System.out.println("after relativeSort w/ a=0,b=4,c=" 
    + testC +"...");
    printArr(arr3);
    System.out.println("-----------------------");

    System.out.println("arr4:");
    printArr(arr4);
    relativeSort(arr4,0,4,testC);
    System.out.println("after relativeSort w/ a=0,b=4,c=" 
    + testC +"...");
    printArr(arr4);
    System.out.println("-----------------------");

    System.out.println("arr5:");
    printArr(arr5);
    relativeSort(arr5,0,4,testC);
    System.out.println("after relativeSort w/ a=0,b=4,c=" 
    + testC +"...");
    printArr(arr5);
    System.out.println("-----------------------");
    }
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
  }//end main

}//end class Mysterion