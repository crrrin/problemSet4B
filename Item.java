/**
* Item Class
* @author Dhanish Azam
* @version 17.0.10
* @since 2024/05/09
*/

public class Item {
  private String productName;
  private int productID;
  private double unitPrice;
  private int stock;
  private int restockLevel;

  private final int originalStock;
  
  /**
   * creates an Item object and stores the name, product ID, unit price, current stock, and restock level of a given product.
   * @param productName the name of the product
   * @param productID the ID of the product
   * @param unitPrice the price of the product per unit
   * @param stock the current quantity of the product in stock
   * @param restockLevel the minimum stock quantity at which a restock will automatically trigger, reordering enough stock to return to the original stock amount
   */
  public Item(String productName, int productID, double unitPrice, int stock, int restockLevel) {
    this.productName = productName;
    this.productID = productID;
    this.unitPrice = unitPrice;
    this.restockLevel = restockLevel;
    
    if (stock < 0){ // if the stock is negative, set it to 0
      stock = 0;
    }
    
    this.stock = stock;
    this.originalStock = stock;

  } 

  /**
   * creates an Item object and stores the name, product ID, unit price, and current stock. Does not take a value for restock level- to be used if an item will not be restocked.
   * @param productName the name of the product
   * @param productID the ID of the product
   * @param unitPrice the price of the product per unit
   * @param stock the current quantity of the product in stock
   */
  public Item(String productName, int productID, double unitPrice, int stock) {
    this.productName = productName;
    this.productID = productID;
    this.unitPrice = unitPrice;
    
    if (stock < 0){
      stock = 0;
    }
    
      this.stock = stock;
      this.originalStock = stock;

    this.restockLevel = -1; // if restocklevel is not stated then it will be assumed that this item will never restock, and be set to -1
  } 

  /**
   * gets the stored name of the product.
   * @return returns the stored name of the product.
   */
  public String getProductName(){
    return this.productName;
  }

  /**
   * gets the stored ID of the product.
   * @return returns the stored ID of the product.
   */
  public int getProductID(){
    return this.productID;
  }

  /**
   * gets the stored price per unit of the product.
   * @return returns the stored price per unit of the product.
   */
  public double getUnitPrice(){
    return this.unitPrice;
  }

  /**
   * gets the stored stock quantity of the product.
   * @return returns the stored stock quantity of the product.
   */
  public int getStock(){
    return this.stock;
  }

  /**
   * gets the stored restocking level of the product. 
   * @return returns the stored restocking level of the product.
   */
  public int getRestockLevel(){
    return this.restockLevel;
  }

  /**
   * sets a new price per unit for the product.
   * @param unitPrice the new price of the product per unit
   */
  public void setUnitPrice(int unitPrice){
    this.unitPrice = unitPrice;
  }

  /**
   * sets a new quantity of the product in stock.
   * @param stock the new quantity of the product in stock
   */
  public void setStock(int stock){
    this.stock = stock;

    if (this.stock <= restockLevel){
      this.stock = this.originalStock;
    }
  }

  /**
   * sets a new restock level for the product.
   * @param restockLevel the new minimum stock quantity at which a restock will automatically trigger, reordering enough stock to return to the original stock amount
   */
  public void setRestockLevel(int restockLevel){
    this.restockLevel = restockLevel;
  }

  /**
   * checks if two items have the same product ID.
   * @param checkItem the Item to be compared against the argument Item
   * @return returns a boolean indicating whether or not the two items have the same 
   */
@Override
  public boolean equals(Object obj){
    Item checkItem = (Item)obj;

    return checkItem.getProductID() == this.productID; // returns if the two items are the same using their product IDs
  }

  /**
   * returns a string containing all data regarding the provided item.
   * @return returns data formatted as:<br />productName(productID) - unitPrice<br />Stock: stock
   */
  @Override
  public String toString(){
    return this.productName + "(" + this.productID + ") - " + this.unitPrice + "\nStock: " + this.stock;
  }
  
}