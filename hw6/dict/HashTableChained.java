/* HashTableChained.java */

package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected static boolean isPrime(int n){
    for (int divisor = 2; divisor < n; divisor++){
      if (n % divisor == 0){
        return false;
      }
    }
    return true;
  }

  protected static int PRIME = 109345121;
  protected SList[] buckets;
  protected int size;
  protected long scale, shift;
  protected int prime, capacity;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    int lower = sizeEstimate;
    int upper = sizeEstimate * 2;

    for (int j = upper; j > lower; j--){
      if(isPrime(j)){
        capacity = j;
        break;
      }
    }

    buckets = new SList[capacity];
    for (int i = 0; i < capacity; i++){
      buckets[i] = new SList();
    }
    java.util.Random rand = new java.util.Random();
    scale = rand.nextInt(PRIME - 1) + 1;
    shift = rand.nextInt(PRIME);
  } 

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    capacity = 101;
    buckets = new SList[capacity];
    for (int i = 0; i < capacity; i++){
      buckets[i] = new SList();
    }
    java.util.Random rand = new java.util.Random();
    scale = rand.nextInt(PRIME - 1) + 1;
    shift = rand.nextInt(PRIME);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return (int) ((Math.abs(code*scale + shift) % PRIME) % capacity);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return size == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry newEntry = new Entry(key, value);
    int code = key.hashCode();
    int hashValue = compFunction(code);
    
    buckets[hashValue].insertBack(newEntry);

    return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int code = key.hashCode();
    int hashValue = compFunction(code);
    ListNode node = buckets[hashValue].front();
    Entry newEntry = new Entry();

    if (node == null){
      return null;
    }else{
      try{
        while(node.isValidNode()){
          if(((Entry) node.item()).key() == key){
            newEntry = (Entry) node.item();
            return newEntry;
          }else{
            node = node.next();
          }
        }
      } catch (InvalidNodeException lbe){}
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int code = key.hashCode();
    int hashValue = compFunction(code);
    ListNode node = buckets[hashValue].front();
    Entry newEntry = new Entry();

    if (node == null){
      return null;
    }else{
      try{
        while(node.isValidNode()){
          if(((Entry) node.item()).key() == key){
            newEntry = (Entry) node.item();
            node.remove();
            return newEntry;
          }else{
            node = node.next();
          }
        }
      } catch (InvalidNodeException lbe){}
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    buckets = new SList[capacity];
    for (int i = 0; i < capacity; i++){
      buckets[i] = new SList();
    }
  }

  public String toString(){
    String s = "";
    int num = 0;

    for(int i = 0; i < capacity; i++){
      s += "[" + buckets[i].length() + "]";
      num++;
      if(num > 15){
        s += "\n";
        num = 0;
      }
    }

    return s;
  }

  public int capacity(){
    return capacity;
  }

  public int collisions(){
    int num = 0;

    for(int i = 0; i < capacity; i++){
      if(buckets[i].length() > 1){
        num++;
      }
    }
    return num;
  }

}
