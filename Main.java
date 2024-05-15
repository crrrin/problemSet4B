
class Main {
  public static void main(String[] args) {
    
    Item[] testItems = new Item[]{
      new Item("Gloves", 113, 8.99, 55, 12),
      new Item("Sneakers", 103, 59.99, 20, 8),
      new Item("Umbrella", 115, 17.99, 40, 9),
      new Item("Jacket", 107, 79.99, 15, 3),
      new Item("Dress", 108, 49.99, 35, 7),
      new Item("Ring", 118, 49.99, 20, 5),
      new Item("Socks", 120, 6.99, 45, 10),
      new Item("Wallet", 111, 24.99, 30, 8),
      new Item("Watch", 110, 99.99, 20, 5),
      new Item("Jeans", 102, 39.99, 30, 5),
      new Item("Necklace", 117, 39.99, 30, 7),
      new Item("Scarf", 114, 12.99, 25, 6),
      new Item("Backpack", 104, 29.99, 25, 6),
      new Item("Hat", 106, 9.99, 60, 15),
      new Item("T-Shirt", 101, 19.99, 50, 10),
      new Item("Earrings", 116, 29.99, 50, 11),
      new Item("Sunglasses", 105, 14.99, 40, 12),
      new Item("Bracelet", 119, 19.99, 35, 8),
      new Item("Boots", 109, 69.99, 10, 2),
      new Item("Belt", 112, 14.99, 45, 10)};

      ItemManagement itemManagement = new ItemManagement(testItems);

// itemManagement.saveData("test.txt");

      System.out.println(itemManagement + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

      itemManagement.sortName();

      System.out.println(itemManagement + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

      itemManagement.loadData("test.txt");
  
      System.out.println(itemManagement);
  


    };
  


    
  }