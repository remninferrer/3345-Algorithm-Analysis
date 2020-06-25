// Project 2 for CS 3345
// Programmer: Remnin Ezekiel Ferrer 
// Algorithm Analysis 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Class is used to take values from functions
class ListData{
	ArrayList <Integer> userList;
	String property = "";
	String sort = "";
	double time = 0;
	int comparisons = 0;
	int movements = 0;
}

public class AlgorithmAnalysis {

	// Shows menu that acts as driver for functions
	public static void displayMenu() {
		
		Scanner keyb = new Scanner (System.in);
		
		// Initialize variables
		int size, property, sort;
		
		do {
			
			// Receive size as user input
			System.out.println("Enter list size..(MIN - 1, MAX - 500000, 0 - EXIT)");			
			size = keyb.nextInt();
			
			if(size == 0) {
				System.out.println("Program will now end..");
				break;
			}
			
			// If size is invalid, ask user for valid input
			while(size < 0 || size > 500000) {
				System.out.println("Error. Enter valid list size (MIN - 1, MAX - 500000, 0 - EXIT)");
			
				size = keyb.nextInt();
			}
			
			// Receive property as user input
			System.out.println("Select a number for the list type..");
			System.out.println("0 - EXIT");
			System.out.println("1 - INORDER");
			System.out.println("2 - REVERSE ORDER");
			System.out.println("3 - ALMOST ORDER");
			System.out.println("4 - RANDOM");
			property = keyb.nextInt();
			
			// If property is invalid, ask user for valid input
			while(property < 0 || property > 4) {
				System.out.println("Error. Enter valid list property..");
				System.out.println("0 - EXIT");
				System.out.println("1 - INORDER");
				System.out.println("2 - REVERSE ORDER");
				System.out.println("3 - ALMOST ORDER");
				System.out.println("4 - RANDOM");
				size = keyb.nextInt();
			}
			
			if(property == 0) {
				System.out.println("Program will now end..");
				break;
			}
			
			// Initialize an instance of ListData class
			ListData list = new ListData();
			
			// Call createList function to create desired list
			list.userList = createList(size,property,list);
			
			System.out.println("Enter a sorting algorithm..");
			System.out.println("0 - EXIT");
			System.out.println("1 - INSERTION SORT");
			System.out.println("2 - SELECTION SORT");
			System.out.println("3 - QUICK SORT");
			System.out.println("4 - MERGE SORT");
			System.out.println("5 - HEAP SORT");
			System.out.println("6 - RADIX SORT");
			
			sort = keyb.nextInt();
			
			while(property < 0 || property > 4) {
				System.out.println("Error. Enter valid list property.Enter a sorting algorithm..");
				System.out.println("0 - EXIT");
				System.out.println("1 - INSERTION SORT");
				System.out.println("2 - SELECTION SORT");
				System.out.println("3 - QUICK SORT");
				System.out.println("4 - MERGE SORT");
				System.out.println("5 - HEAP SORT");
				System.out.println("6 - RADIX SORT");
				size = keyb.nextInt();
			}
			
			if(sort == 0) {
				System.out.println("Program will now end..");
				break;
			}
			// Call corresponding sort function and record time
			else if (sort == 1) {
				list.sort = "Insertion Sort";
				long startTime = System.nanoTime();
				insertionSort(list);
				list.time = (System.nanoTime() - startTime) / 1000000000.0;
			}
			else if (sort == 2) {
				list.sort = "Selection Sort";
				long startTime = System.nanoTime();
				selectionSort(list);
				list.time = (System.nanoTime() - startTime) / 1000000000.0;
			}
			else if (sort == 3) {
				list.sort = "Quick Sort";
				long startTime = System.nanoTime();
				quickSort(list, 0, size - 1);
				list.time = (System.nanoTime() - startTime) / 1000000000.0;
			}
			else if (sort == 4) {
				list.sort = "Merge Sort";
				long startTime = System.nanoTime();
				mergeSort(list, 0, size - 1);
				list.time = (System.nanoTime() - startTime) / 1000000000.0;
			}
			else if (sort == 5) {
				long startTime = System.nanoTime();
				list.sort = "Heap Sort";
				heapSort(list);
				list.time = (System.nanoTime() - startTime) / 1000000000.0;
			}
			else if (sort == 6) {
				list.sort = "Radix Sort";
				long startTime = System.nanoTime();
				
				radixSort(list, list.userList.size());
				list.time = (System.nanoTime() - startTime) / 1000000000.0;
			}
			
			for(int i = 0; i < list.userList.size(); i++) {
				System.out.println(list.userList.get(i));
			}
			
			printList(list);
			
		} while(property != 0 || /**sort != 0 ||**/ size != 0);
		
	}
	
	// Returns a generated list depending on option of user
	public static ArrayList<Integer> createList(int size, int property, ListData list) {
		
		ArrayList <Integer> toReturn = new ArrayList<Integer>();
		
		// Create list with in order as default
		for (int i = 1; i < size + 1; i++) {
			toReturn.add(i);	
		}

		// In Order
		if(property == 1) {
			list.property = ("In Order");
		}
		// Reverse order
		if(property == 2) {
			Collections.reverse(toReturn);
			list.property = ("Reverse");
		}
		// Almost in order
		else if(property == 3) {
			// Get 20% of array index in the middle
			int lowerBound = 2 * size / 5;
			int upperBound = (3 * size / 5) + 1;
			
			Collections.shuffle(toReturn.subList(lowerBound, upperBound));
			list.property = "Almost In Order";
		}
		// Random order
		else if (property == 4) {
			Collections.shuffle(toReturn);
			list.property = "Random";
		}
		
		return toReturn;
	}
	
	// Prints list with list details
	public static void printList(ListData list) {
		
		System.out.println("\nSize: " + list.userList.size());
		System.out.println("Order: " + list.property);
		System.out.println("Sort: " + list.sort);
		System.out.println("Comparisons: " + list.comparisons);
		System.out.println("Movements: " + list.movements);
		System.out.println("Total time (secs) : " + list.time + "\n");
	}
	
	// Sorts the list using insertion sort, also counts movements and comparisons
	public static void insertionSort(ListData list) {
		
        for (int i = 1; i < list.userList.size(); i++) { 
            int key = list.userList.get(i); 
            int j = i - 1; 
            
            while (j >= 0 && list.userList.get(j) > key) { 
            	list.userList.set(j + 1, list.userList.get(j));
                
            	j = j - 1; 
                
                list.comparisons++;
                list.movements++;
            } 
            list.comparisons++;
            
            list.userList.set(j + 1, key);
            
        } 
		
	}
	
	// Sorts the list using selection sort, also counts movements and comparisons
	public static void selectionSort(ListData list) {
		
        for (int i = 0; i < list.userList.size()-1; i++) 
        { 
            int min = i; 
            for (int j = i + 1; j < list.userList.size(); j++) {
                if (list.userList.get(j) < list.userList.get(min)) {
                	min = j;
                }
                list.comparisons++;
            }         
   
            int temp = list.userList.get(min); 
            list.userList.set(min,list.userList.get(i));
            list.userList.set(i,temp);
            
            list.movements++;
        } 
		
	}
	
	// Sorts the list using quick sort, also counts movements and comparisons
	public static void quickSort(ListData list, int low, int high) {
		
        if (low < high) { 
            
            int partitionIndex = QSpartition(list, low, high); 
            list.comparisons++;
            
            quickSort(list, low, partitionIndex-1); 
            quickSort(list, partitionIndex+1, high); 
        } 
		
	}
	
	// Partitioner for Quick Sort
	public static int QSpartition(ListData list, int low, int high) { 
        int pivot = list.userList.get(high);  
        int i = (low - 1); 
        for (int j = low; j < high; j++) 
        { 

            if (list.userList.get(j) <= pivot) 
            { 
                i++; 
  
                int temp = list.userList.get(i); 
                list.userList.set(i, list.userList.get(j));
                list.userList.set(j, temp); 
            } 
            list.comparisons++;
        } 
  
        int temp = list.userList.get(i + 1); 
        list.userList.set(i + 1, list.userList.get(high)); 
        list.userList.set(high, temp); 
        list.movements++;
  
        return i + 1; 
    } 
	
	// Sorts the list using merge sort
	public static void mergeSort(ListData list, int left, int right) {
	
		list.comparisons++;
		if (left < right) { 
	        list.comparisons++;
	        int mid = left +(right - left)/2; 
	  
	        mergeSort(list, left, mid); 
	        mergeSort(list, mid + 1, right); 
	  
	        MSsort(list, left, mid, right); 
	    } 
	}
	
	// Merger for Merge Sort
	public static void MSsort(ListData list, int left, int mid, int right) { 
 
        int n1 = mid - left + 1; 
        int n2 = right - mid; 
        
        ArrayList <Integer> Left = new ArrayList <Integer>(n1);
        ArrayList <Integer> Right = new ArrayList <Integer>(n2); 
  
        for (int i = 0; i < n1; ++i) {
        	Left.add(list.userList.get(left + i));
        } 
        for (int j = 0; j < n2; ++j) {
        	Right.add(list.userList.get(mid + j + 1));
        }
  
        int i = 0, j = 0; 
  

        int k = left; 
        
        list.comparisons++;
        while (i < n1 && j < n2) 
        { 
            if (Left.get(i) <= Right.get(j)) 
            { 
            	list.userList.set(k, Left.get(i)); 
                i++; 
            } 
            else
            { 
            	list.userList.set(k, Right.get(j));
                j++; 
            } 
            k++; 
            list.movements++;
            list.comparisons++;
        } 
 
        list.comparisons++;
        while (i < n1) 
        { 
        	list.userList.set(k, Left.get(i));  
            i++; 
            k++; 
            list.comparisons++;
            list.movements++;
        } 
        
        list.comparisons++;
        while (j < n2) 
        { 
        	list.userList.set(k, Right.get(j));
            j++; 
            k++; 
            list.comparisons++;
            list.movements++;
        } 
    } 
	
	 
	public static void heapSort(ListData  list) {
		
		int n = list.userList.size(); 
		  
	    for (int i = n / 2 - 1; i >= 0; i--) {
	       	HSheapifier(list, n, i);
	       	list.comparisons++;
	    }
	             
         
        for (int i=n-1; i>=0; i--) { 
	             
	            int temp = list.userList.get(0); 
	            list.userList.set(0, list.userList.get(i));
	            list.userList.set(i, temp);
	            
	            list.movements++;
	            list.comparisons++;
	              
	            HSheapifier(list, i, 0); 
	     } 
	}
	
	// Heapifier for Heap Sort
	public static void HSheapifier(ListData list, int n, int i) { 
	        int largest = i;  
	        int left = 2 * i + 1;  
	        int right = 2 * i + 2; 
	  
	        
	        if (left < n && list.userList.get(left) > list.userList.get(largest)) {
	        	largest = left;
	        }
	        list.comparisons++;     
	  
	        if (right < n && list.userList.get(right) > list.userList.get(largest)) {
	        	largest = right;
	        }
	        list.comparisons++;     
	        
	        if (largest != i) 
	        { 	
	        	int temp = list.userList.get(i); 
	            list.userList.set(i, list.userList.get(largest));
	            list.userList.set(largest, temp);
	            
	            list.comparisons++;
	            list.movements++;
	            
	            HSheapifier(list, n, largest); 
	        } 
	    }

	public static void radixSort(ListData list, int size) {
	
        int max = RSmax(list, size); 
  
        for (int exponent = 1; max/exponent > 0; exponent *= 10) {
        	RSsort(list, size - 1, exponent);
        }
	}
	
	// Finds max for Radix Sort
	public static int RSmax(ListData list, int size) {
    
		int max = list.userList.get(0); 
        for (int i = 1; i < size; i++) {
        	if (list.userList.get(i) > max) {
        		max = list.userList.get(i);
        	}
        list.comparisons++;
        }        
        return max; 
	}
	
	// Counts and sorts index
	public static void RSsort(ListData list, int size, int exponent) { 
      
        ArrayList <Integer> output = new ArrayList <Integer>();
        ArrayList <Integer> count = new ArrayList <Integer>();
        int i; 
        
        for(int j = 0; j < 10; j++) {
        	count.add(0);
        }
        
        for(int k = 0; k < size; k++) {
        	output.add(0);
        }
        
        list.comparisons++;
        for (i = 0; i < size; i++) {
        	count.set((list.userList.get(i) / exponent) % 10, count.get((list.userList.get(i) / exponent) % 10) + 1);
        	list.comparisons++;
        }
             
        for (i = 1; i < 10; i++) {
        	count.set(i, count.get(i) + count.get(i - 1));
        	list.comparisons++;
        	//list.movements++;
        }
  		
        for (i = size - 1; i >= 0; i--) { 
        	output.set(count.get((list.userList.get(i) / exponent) % 10) - 1, list.userList.get(i)); 
        	count.set((list.userList.get(i) / exponent) % 10, count.get((list.userList.get(i) / exponent) % 10)- 1);
        	list.movements++;
        	list.comparisons++;
        } 
  
        for (i = 0; i < size; i++) {
        	list.userList.set(i, output.get(i));
        	list.movements++;
        	list.comparisons++;
        }
    } 
  
	
	public static void main (String args[]) {
		displayMenu();
	}
}
