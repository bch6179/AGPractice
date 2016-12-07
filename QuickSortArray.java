/ Quicksort.java
// Class demo by THC.
// Performs the quicksort algorithm to sort an array a[0..n-1].
// Uses Lomuto's partitioning algorithm (see Chapter 7 of CLRS, Second Edition).

@SuppressWarnings({"rawtypes", "unchecked"})

public class Quicksort implements Sorter {
  // Sort the array a[0..n-1] by the quicksort algorithm.
  public void sort(Comparable [] a, int n) {
    quicksort(a, 0, n-1);
  }
 
  // Sort the array a[p..r] by the quicksort algorithm.
  // Partition a[p..r] into subarrays a[p..q-1] and a[q+1..r], where p <= q <= r, and
  // each element of a[p..q-1] is less than or equal to a[q], which is less than
  // each element of a[q+1..r].
  // Then recursively sort a[p..q-1] and a[q+1..r].
  private void quicksort(Comparable [] a, int p, int r) {
    if (p < r) {              // nothing to do if the subarray has fewer than 2 elements
      int q = partition(a, p, r); // q is pivot position
      quicksort(a, p, q-1);       // recursively sort a[p..q-1]
      quicksort(a, q+1, r);       // recursively sort a[q+1..r]
    }
  }
 
  // Partition a[p..r] into subarrays a[p..q-1] and a[q+1..r], where p <= q <= r, and
  // each element of a[p..q-1] is less than or equal to a[q], which is less than
  // each element of a[q+1..r].
  // Works by selecting the original value of a[r] as the pivot element and
  // partitioning a[p..r] around the pivot.
  // Returns the value of q that marks the partition.
  protected int partition(Comparable [] a, int p, int r) {
    Comparable pivot = a[r];        // the value we pivot around
    int i = p - 1;                  // i is index into left side
   
    // The following for loop maintains the following invariants:
    // 1. Every element of a[p..i] is less than or equal to the pivot.
    // 2. Every element of a[i+1..j-1] is greater than the pivot.
    // 3. a[r] equals the pivot.
    for (int j = p; j < r; j++) {   // j is index into right side
      // Find out which side a[j] goes into.  If the left side, then we have
      // to increment the size of the left side and then get a[j] into position i.
      // If the right side, a[j] is already where we want it, so just incrementing
      // j in the loop header suffices.
      if (a[j].compareTo(pivot) <= 0) { // which side does a[j] go into?
        i++;                            // if left side, make it one larger...
        swap(a, i, j);                  // ...and get a[j] into the left side
      }
    }
   
    // We dropped out of the loop because j == r.  Every element of a[p..i]
    // is less than or equal to the pivot, and every element of a[i+1..r-1] is
    // greater than the pivot.  If we put the pivot into position i+1, then we
    // have what we want: a[p..i] is less than or equal to the pivot, a[i+1]
    // equals the pivot, and a[i+2..r] is greater than the pivot.
    swap(a, i+1, r);
   
    // Return the index of where the pivot ended up.
    return i+1;
  }

  // Swap two locations i and j in array a.
  protected void swap(Comparable [] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }
}