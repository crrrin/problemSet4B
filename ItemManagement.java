/**
 * ItemManagement Class
 * Manages a database of Item objects and provides methods to add, 
 * sell, search, display, load, and save item data.
 * @author Dhanish Azam
 * @version 17.0.10
 * @since 2024/05/09
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ItemManagement {

  private ArrayList<Item> items; // initialize empty arraylist to use as database

  /**
   * creates a database of Item objects with the provided array of Item objects
   * @param items an arraylist of Item objects
   */
  public ItemManagement(Item[] items) {
    this.items = new ArrayList<Item>();

    for (int i = 0; i < items.length; i++){ // write the array to an arraylist to make adding new items more convenient
      this.items.add(items[i]);
    }
  }

  /**
   * creates an empty database of Item objects.
   */
  public ItemManagement() {

  }

  /**
   * Adds an item to the argument Item database. If said item already exists, then sum their stocks and update said item.
   * @param item the item to be added to the argument Item database
   */
  public void addItem(Item item) {
    if (!this.items.contains(item)) { // if the item is new, add it
      this.items.add(item);
    } 
    else { // if it isnt new, find the index of this item,
      for (int i = 0; i < this.items.size(); i++) {
        if (this.items.get(i).equals(item)) {

          // and set their stock to the sum of the new stock and the existing stock
          item.setStock(item.getStock() + this.items.get(i).getStock());
        }
      }
    }
  }

  /**
   * Reduces the stock of an item if it is permitted to do so.
   * @param id the id of the item to have its quantity reduced
   * @param quantity the quantity to reduce the item by
   * @return returns whether or not the stock was able to be reduced or not
   */
  public boolean sellItem(int id, int quantity) {

    int index = -1;

    for (int i = 0; i < this.items.size(); i++) { // find the index of the product using ID
      if (this.items.get(i).getProductID() == id) {
        index = i;
        i = this.items.size();
      }
    }

    if (index < 0) { // if it isnt in the array return that the reduction of stock didnt occur
      return false;
    }

    // if the current stock is greater than or equal to the reduction amount, let the reduction proceed
    if (this.items.get(index).getStock() >= quantity) {

      this.items.get(index).setStock(
          this.items.get(index).getStock() - quantity);

      return true;
    } 
    else { // if not, return that the reduction did not take place
      return false;
    }
  }

  /**
   * Searches for an item by name. returns the corresponding object if it can be located.
   * @param name the name of the object to be found
   * @return returns the searched object if it can be located
   */
  public Item SearchStockItem(String name) {
    for (int i = 0; i < this.items.size(); i++) {
      if (name.toLowerCase().equals(this.items.get(i).getProductName().toLowerCase())) {
        return this.items.get(i);
      }
    }
    return null;
  }

  /**
   * Searches for an item by product ID. returns the corresponding object if it can be located.
   * @param ID the ID of the object to be found
   * @return returns the searched object if it can be found
   */
  public Item SearchStockItem(int ID) {
    for (int i = 0; i < this.items.size(); i++) {
      if (ID == this.items.get(i).getProductID()) {
        return this.items.get(i);
      }
    }
    return null; // return null if an object with the parameter cant be located
  }

  public String toString() { 
    String returnString = "";

    for (int i = 0; i < items.size(); i++) {
      returnString += this.items.get(i).toString() + "\n";

      if (!(i == this.items.size() - 1)) { // if we're on the last item, add an extra new line
        returnString += "\n";
      }
    }

    return returnString;
  }

  /**
   * calls the mergesort function on the argument Item array with the Name parameter; sorts by name, lexicographically.
   */
  public void sortName() {
    mergeSort("Name");
  }

  /**
   * calls the mergesort function on the argument Item array with the ID parameter; sorts by ID, in ascending order.
   */
  public void sortID() {
    mergeSort("ID");
  }

  /**
   * calls the mergesort function on the argument Item array with the PriceAsc (Ascending Price) parameter; sorts by unit price, in ascending order.
   */
  public void sortPriceAscending() {
    mergeSort("PriceAsc");
  }

  /**
   * calls the mergesort function on the argument Item array with the PriceDesc (Descending Price) parameter; sorts by unit price, in Descending order.
   */
  public void sortPriceDescending() {
    mergeSort("PriceDesc");
  }

  /**
   * Returns an Item array of all items in the argument array
   * @param showOutOfStock boolean that declares whether or not to include Items with a stock of 0
   * @return returns an Item array of all items in the argument array
   */
  public Item[] displayItems(boolean showOutOfStock) {
    int arraySize = 0;

    for (int i = 0; i < this.items.size(); i++) {
      if (!(this.items.get(i).getStock() == 0)) {
        arraySize++;
      } else if (this.items.get(i).getStock() == 0 && showOutOfStock) {
        arraySize++;
      }
    }

    Item[] displayItemsArray = new Item[arraySize];

    for (int i = 0; i < this.items.size(); i++) {

      if (!(this.items.get(i).getStock() == 0)) {
        displayItemsArray[i] = this.items.get(i);
      } else if (this.items.get(i).getStock() == 0 && showOutOfStock) {
        displayItemsArray[i] = this.items.get(i);
      }
    }
    return displayItemsArray;
  }

  /**
   * calls mergeSortHelper with the item arraylist, size of array, and type of sort parameters.
   * @param items item arraylist to be sorted
   * @param type the type of sort requested; valid parameters are "Name", "ID", "PriceAsc", and "PriceDesc".
   */
  private void mergeSort(String type) {
    mergeSortHelper(this.items, this.items.size(), type);
  }

  /**
   * recursive helper function for mergeSort. 
   * @param items item arraylist to be sorted
   * @param size size of the item arraylist
   * @param type the type of sort requested; valid parameters are "Name", "ID", "PriceAsc", and "PriceDesc".
   */
  private void mergeSortHelper(ArrayList<Item> items, int size, String type) {
    // base case
    if (size < 2) {
      return;
    }

    int middle = size / 2; // divide the size in half to get a middle point
    ArrayList<Item> left_array = new ArrayList<>(middle); // left array for the first half of the array
    ArrayList<Item> right_array = new ArrayList<>(size - middle); // right array for the second half

    for (int i = 0; i < middle; i++) { // copy elements from first half into left array
      left_array.add(items.get(i));
      // left_array[i] = array[i];
    }

    for (int i = middle; i < size; i++) { // copy elements from second half into right array
      right_array.add(items.get(i));
      // right_array[i - middle] = array[i];
    }

    // Recursive call here to split up the arrays
    mergeSortHelper(left_array, middle, type);
    mergeSortHelper(right_array, size - middle, type);

    // after our recursive calls, is when the left and right array are now sorted.
    merge(left_array, right_array, items, middle, size - middle, type);

  }

  /**
   * merging method for mergeSortHelper method
   * @param left Left index
   * @param right Right index
   * @param og original arraylist
   * @param leftSize size of left arraylist
   * @param rightSize size of right arraylist
   * @param type the type of sort requested; valid parameters are "Name", "ID", "PriceAsc", and "PriceDesc".
   */
  private void merge(ArrayList<Item> left, ArrayList<Item> right, ArrayList<Item> og, int leftSize, int rightSize,
      String type) {

    // Create three indexes for comparisons and placement in our array
    int leftIndex = 0;
    int rightIndex = 0;
    int ogIndex = 0;

    while (leftIndex < leftSize && rightIndex < rightSize) {
      switch (type) {
        case "Name":
          // if the object name at the left index is lexicographically prioritized, take the next value from the left object
          // if not, take the next object from the right index
          if (left.get(leftIndex).getProductName().compareToIgnoreCase(right.get(rightIndex).getProductName()) < 0) {
            og.set(ogIndex++, left.get(leftIndex++));
          } else {
            og.set(ogIndex++, right.get(rightIndex++));
          }
          break;
        case "ID":
          if (left.get(leftIndex).getProductID() < right.get(rightIndex).getProductID()) {
            og.set(ogIndex++, left.get(leftIndex++));
          } else {
            og.set(ogIndex++, right.get(rightIndex++));
          }
          break;
        case "PriceAsc":
          if (left.get(leftIndex).getUnitPrice() < right.get(rightIndex).getUnitPrice()) {
            og.set(ogIndex++, left.get(leftIndex++));
          } else {
            og.set(ogIndex++, right.get(rightIndex++));
          }
          break;
        case "PriceDesc":
          if (left.get(leftIndex).getUnitPrice() > right.get(rightIndex).getUnitPrice()) {
            og.set(ogIndex++, left.get(leftIndex++));
          } else {
            og.set(ogIndex++, right.get(rightIndex++));
          }
          break;
      }
    }

    // Add the rest of the elements left into the original array, since they are
    // already sorted.
    while (leftIndex < leftSize) { // If left index was the one that ended the prior while loop, this doesn't run
                                   // because its already false
      og.set(ogIndex++, left.get(leftIndex++));
      // og[ogIndex++] = left[leftIndex++];
    }
    while (rightIndex < rightSize) {
      og.set(ogIndex++, right.get(rightIndex++));
      // og[ogIndex++] = right[rightIndex++];
    }

  }

  /**
   * loads data from the provided file into the Item database.
   * @param fileName the file to target when attempting to load data
   */
  public void loadData(String fileName) {
    Scanner fileScanner = null;

    try {
      // create scanner and printwriter objects
      fileScanner = new Scanner(new BufferedReader(new FileReader(fileName)));

      while (fileScanner.hasNext()) {

        // adds items from the file following the format
        // <name>
        // <id> <unit price> <stock> <restock level>
        addItem(new Item(fileScanner.nextLine(), fileScanner.nextInt(), fileScanner.nextDouble(), fileScanner.nextInt(), fileScanner.nextInt()));

        fileScanner.nextLine(); // clear the scanner and go to the next line
      }

    } catch (Exception e) {
    } finally {
      if (fileScanner != null){
        fileScanner.close();
      }
    }
  }

  /**
   * saves data from the Item database into the specified file.
   * @param fileName the file to target when attempting to save data
   */
  public void saveData(String fileName){
    PrintWriter fileWriter = null;

    String toWrite = "";

    // follows the format:
    // <name>
    // <id> <unit price> <stock> <restock level>
    for (int i = 0; i < this.items.size(); i++){
        toWrite += this.items.get(i).getProductName() + "\n" + this.items.get(i).getProductID() + " " + this.items.get(i).getUnitPrice() + " " + this.items.get(i).getStock() + " " + this.items.get(i).getRestockLevel() + "\n";
      }

    try {
      fileWriter = new PrintWriter(new FileWriter(fileName, false));

      fileWriter.print(toWrite);
      
    } catch (Exception e) {} finally {
      if (fileWriter != null) {
          fileWriter.close();
      }
    }
    
  }

}