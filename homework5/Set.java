/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
 @SuppressWarnings("unchecked")
public class Set {
  /* Fill in the data fields here. */
  private int size;
  private List setList;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    setList=new DList();
    size=setList.length();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return setList.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    if(c!=null){
    	if(setList.isEmpty()){
    		setList.insertFront(c);
    		return;
    	}else{
    		ListNode node=setList.front();
    		try{
	    		while(node.isValidNode()){
	    			if(((Comparable)node.item()).compareTo(c)==0)
	    				return;
	    			else if(((Comparable)node.item()).compareTo(c)<0)
	    				node=node.next();
	    			else{
	    				node.insertBefore(c);
	    				return;
	    			}
	    		}
	    		setList.insertBack(c);
	    		return;
	    	}catch(InvalidNodeException e){
	    		e.printStackTrace();
	    	}
	    }
    }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
    ListNode n1=setList.front();
    ListNode n2=s.setList.front();
    
    try{
	    while(n2.isValidNode()){
	    	while(n1.isValidNode()){
	    		if(((Comparable)n1.item()).compareTo(n2.item())==0){
	    			n1=n1.next();
	    			n2=n2.next();
	    		}else if(((Comparable)n1.item()).compareTo(n2.item())>0){
	    			n1.insertBefore(n2.item());
	    			n2=n2.next();
	    		}else{
	    			n1=n1.next();
	    		}
	    	}
	    	setList.insertBack(n2.item());   
	    	n2=n2.next();
	    }
	  }catch(InvalidNodeException e){
		  e.printStackTrace();
	  }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
    ListNode n1=setList.front();
    ListNode n2=s.setList.front();
    try{
    	while(n1.isValidNode()){
	    	while(n2.isValidNode()){
	    		if(((Comparable)n1.item()).compareTo(n2.item())==0){
	    			if(!n1.next().isValidNode())	
	    				return;//保证两个while之间嵌套的代码只会在n1还剩元素时执行
	    			n1=n1.next();
	    			n2=n2.next();
	    		}else if(((Comparable)n1.item()).compareTo(n2.item())>0){
	    			n2=n2.next();
	    		}else{
	    	    	ListNode tnode=n1;
	    			n1=n1.next();
	    			tnode.remove();
	    		}
	    	}
	    	ListNode tnode=n1;
				n1=n1.next();
				tnode.remove();
    	}
	  }catch(InvalidNodeException e){
		  e.printStackTrace();
	  }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    String str = " ";
    ListNode curr = setList.front();

    while (curr.isValidNode()) {
        try {
            str += curr.item() + " ";
            curr = curr.next();
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        }
    }
    return "{" + str + "}";
  }

  public static void main(String[] argv) {
	    System.out.println("Testing insert()");
	    Set s = new Set();
	    s.insert(new Integer(3));
	    s.insert(new Integer(4));
	    s.insert(new Integer(3));
	    System.out.println("Set s should be { 3 4 }: " + s);

	    Set s2 = new Set();
	    s2.insert(new Integer(4));
	    s2.insert(new Integer(5));
	    s2.insert(new Integer(5));
	    System.out.println("Set s2 should be { 4 5 }: " + s2);

	    Set s3 = new Set();
	    s3.insert(new Integer(5));
	    s3.insert(new Integer(3));
	    s3.insert(new Integer(8));
	    System.out.println("Set s3 should be { 3 5 8 }: " + s3);

	    System.out.println();
	    System.out.println("Tesing union()");
	    s.union(s2);
	    System.out.println("After s.union(s2), s should be { 3 4 5 }: " + s);
	    s2.union(s3);
	    System.out.println("After s2.union(s3), s2 should be { 3 4 5 8 }: " + s2);
	    Set s4 = new Set();
	    System.out.println("Empty set s4 = " + s4);
	    s.union(s4);
	    System.out.println("After s.union(s4), s should be { 3 4 5 }: " + s);
	    s4.union(s);
	    System.out.println("After s4.union(s), s4 should be { 3 4 5 }: " + s4);

	    System.out.println();
	    System.out.println("Tesing intersect()");
	    Set s5 = new Set();
	    Set s6 = new Set();
	    s6.insert(new Integer(1));
	    s5.intersect(s6);
	    System.out.println("{}.intersect({1}) should be { }: " + s5);
	    s6.intersect(s5);
	    System.out.println("{1}.intersect({}) should be { }: " + s6);
	    s6.insert(new Integer(1));
	    Set s7 = new Set();
	    s7.insert(new Integer(1));
	    s7.insert(new Integer(2));
	    s6.intersect(s7);
	    System.out.println("{1}.intersect({1 2}) should be { 1 }: " + s6);
	    s7.intersect(s6);
	    System.out.println("{1 2}.intersect({1}) should be { 1 }: " + s7);
	    s6.insert(new Integer(2));
	    s6.insert(new Integer(3));
	    s7.insert(new Integer(2));
	    s6.intersect(s7);
	    System.out.println("{1 2 3}.intersect({1 2}) should be { 1 2 }: " + s6);
	    s6.insert(new Integer(3));
	    s6.insert(new Integer(5));
	    s7.insert(new Integer(4));
	    s7.insert(new Integer(7));
	    s7.intersect(s6);
	    System.out.println("{1 2 4 7}.intersect({1 2 3 5}) should be { 1 2 }: " + s7);

	    System.out.println();
	    System.out.println("Tesing cardinality()");
	    System.out.println("s.cardinality() should be 3: " + s.cardinality());
	    System.out.println("s4.cardinality() should be 3: " + s4.cardinality());
	    System.out.println("s5.cardinality() should be 0: " + s5.cardinality());
	    System.out.println("s6.cardinality() should be 4: " + s6.cardinality());
	    System.out.println("s7.cardinality() should be 2: " + s7.cardinality());
	  }
}
