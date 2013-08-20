import java.util.Random;
import java.util.Date;
import lab6.trees.*;

public class SearchingExperiments {
 public static final int NUMBER_OF_NUMBERS = 100000;
 public static final int NUMBER_OF_CHOICES = 100000;
 public static Long[] unsorted = new Long[NUMBER_OF_NUMBERS];
 public static int[] choices = new int[NUMBER_OF_CHOICES];
 public static Long[] numbers = new Long[NUMBER_OF_NUMBERS];
 public static Long[] sorted = new Long[NUMBER_OF_NUMBERS];
 public static BSTInterface<Long> bst = 
   new BinarySearchTree<Long>();
 public static BSTInterface<Long> bstBalanced = 
   new BinarySearchTree<Long>();
 public static long startTime;
 public static Long[] array;
 
 public static void main(String[] args) {
  Random random = new Random(123999123L);
  //Filling numbers[]
  for(int i = 0; i < numbers.length; i++)
   numbers[i] = random.nextLong();
  //Filling choices[]
  for(int i = 0; i < choices.length; i++)
   choices[i] = random.nextInt(NUMBER_OF_NUMBERS);
  //Filling unsorted[]
  for(int i = 0; i < unsorted.length; i++)
   unsorted[i] = numbers[i]; 
  //Filling sorted[]
  for(int i = 0; i < sorted.length; i++)
   sorted[i] = numbers[i];
  
  sort(sorted);
  
  //Filling bst
  for(int i = 0; i < unsorted.length; i++)
   bst.add(numbers[i]);
  //Filling bstBalanced
  for(int i = 0; i < sorted.length; i++)
   bstBalanced.add(numbers[i]);
  
  balance();
  
  System.out.println("height bst = " + bst.height());
  System.out.println("height bstBalanced = " 
  + bstBalanced.height());
  
  System.out.println("Experiments begin:");
  
  System.out.println("Unsorted = " + unsortedExperiment() + 
    " milliseconds"); 
  System.out.println("Sorted = " + sortedExperiment() +
    " milliseconds");
  System.out.println("Unbalanced bst = " + bstExperiment(bst) +
    " milliseconds");
  System.out.println("Balanced bst = " +bstExperiment(bstBalanced) +
    " milliseconds");
  
 }
  
  public static long unsortedExperiment(){
   startTime = (new Date()).getTime();
   int choice;
   Long number;
   for(int i = 0; i < NUMBER_OF_CHOICES; i++)
   {
    choice = choices[i];
    number = numbers[choice];
    if(!linearSearch(number))
    {
     System.out.println("Not found");
     break;
    }
    else if(linearSearch(123L))
    {
     System.out.println("123L found");
     break;
    }
     
   }
   return (new Date()).getTime() - startTime;
  }
  
  public static long sortedExperiment(){
    startTime = (new Date()).getTime();
    int choice;
    Long number;
    for(int i = 0; i < NUMBER_OF_CHOICES; i++)
    {
      choice = choices[i];
     number = numbers[choice];
     if(!binarySearch(number))
     {
      System.out.println("Not found");
      break;
     }
     else if(binarySearch(123L))
     {
      System.out.println("123L found");
      break;
     } 
    }
    return (new Date()).getTime() - startTime;  
  }
  
  public static long bstExperiment(BSTInterface<Long> tree)
  {
   int choice;
   Long number;
   startTime = (new Date()).getTime();
   for(int i = 0; i < NUMBER_OF_CHOICES; i++)
   {
    choice = choices[i];
    number = numbers[choice];
    if(!tree.contains(number))
    {
     System.out.println("Not found");
     break;
    }
    else if(tree.contains(123L))
    {
     System.out.println("123L found");
     break;
    }
     
   }
   return (new Date()).getTime() - startTime;
  }
 
  private static boolean linearSearch(Long number)
  {
   for(int j = 0; j < unsorted.length; j++)
    {
     if(unsorted[j] == number)
     {
      return true;
     }
    }
    return false;
  }
  
  private static boolean binarySearch(Long number)
  {
   return binarySearch1(number, 0, sorted.length-1);
  }
  
  private static boolean binarySearch1(Long number, int lo, int hi)
  {
   if (lo > hi)
      return false;
    int location = (lo + hi) / 2;
    if (sorted[location]==number)
      return true;
    if (sorted[location] < number)
      return binarySearch1(number, location+1, hi);
    return binarySearch1(number, lo, location-1);
  }
    
 public static void sort (Long[] array){
  System.out.println("Sorting sorted[] ...");
  for(int i = 0; i <array.length-1; i++)
   for(int j = i+1; j<array.length; j++)
    if(array[i] > array[j])
    {
     Long t = array[i];
     array[i] = array[j];
     array[j] = t;
    }
  
 }
 
 public static void balance(){
  System.out.println("Balancing bstBalanced ...");
  int n = bstBalanced.reset(1);
  array = new Long[n];
  for(int i = 0; i < n; i++)
   array[i] = bstBalanced.getNext();
  bstBalanced = new BinarySearchTree<Long>();
  insertTree(0, n-1);
  
 }
 
 private static void insertTree(int lo, int hi)
    {
  if(lo <= hi){
      int mid = (lo+hi)/2;
      bstBalanced.add(array[mid]);
      insertTree(lo, mid-1);
      insertTree(mid+1, hi);
      
      
     }
    }
    
 
 
    
     
    
    
}

 
 
