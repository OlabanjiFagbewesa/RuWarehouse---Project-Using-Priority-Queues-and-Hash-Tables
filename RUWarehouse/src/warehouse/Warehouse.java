package warehouse;

/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD

        // int n = StdIn.readInt(); // represents the N lines of code.
        // day = StdIn.readInt(); // representing the day through an int (day of the item to add.)
        // id = StdIn.readInt(); // represents the id number of the item to add.
        // name = StdIn.readString(); // name of the item to add (guaranteed to not contain spaces).
        // stock = StdIn.readInt(); // represents the initial item stock to be added.
        // demand = StdIn.readInt(); // represents the intitial item demand.
        sectors[id%10].add(new Product(id, name, stock, day, demand));
    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
        // IMPLEMENT THIS METHOD
        // Sector secObj = new Sector();
        // int size = secObj.getSize();
        // for (int i = 1; i<size; i++){
        //     sectors[i].swim(id);
        //     System.out.print("hello");
        // }
        sectors[id%10].swim(sectors[id%10].getSize());

        // id%10 gets the sector number (0-9), last digit
        // the index%10 gets the category or sector, and we use .getSize() because we want to actually find the specificic
        // index we want to swim up the last integer id to.
    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
       // IMPLEMENT THIS METHOD
       if (sectors[id%10].getSize()==5){
        sectors[id%10].swap(1, 5);
        sectors[id%10].deleteLast();
        sectors[id%10].sink(1);
        // sectors[id%10].swap(id, sectors[id%10].getSize());
        // sectors[id%10].deleteLast();
        // sectors[id%10].sink(sectors[id%10].getSize());
       }
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        // IMPLEMENT THIS METHOD
        
        // takes in a product ID and some amount to restock.
        // then, it updates the stock of that item in the Warehouse accordingly.
        // if item doesn't exist in warehouse it does nothing.

        // method does not move around products because it doesn't affect the popularity of item:
        for (int i=1; i<=sectors[id%10].getSize(); i++){
            // System.out.println(sectors[id%10].get(i).getId());
            // System.out.println("id: "+ id);
            if (sectors[id%10].get(i).getId()==id){
                Product product1 = sectors[id%10].get(i);
                product1.updateStock(amount);
                break;
            }
        }
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        // IMPLEMENT THIS METHOD

        // takes in a product ID, then removes product from your Warehouse
        // product will not neccessarily be the least popular item in its sector

        // of item doesn't exist in warehouse (.getSize for loop), do nothing (with if condition.)

        // once you find item, delete it in O(logn) time by first swapping it with the last element in the sector, reducing the sector

        // size, and then fixing the heap accordingly.


        for (int i =1 ; i<=sectors[id%10].getSize(); i++){
            if (sectors[id%10].get(i).getId()==id){
                sectors[id%10].swap(i, sectors[id%10].getSize());
                sectors[id%10].deleteLast();
                sectors[id%10].sink(i);
                break;
            }
        }
    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        // IMPLEMENT THIS METHOD

        // simulates the purchase order:
        // when an item is purchased, its last purchase day is updated to the current day,






        for (int i =1; i<=sectors[id%10].getSize(); i++){
            // if(sectors[id%10].get(i).getStock()>amount || sectors[id%10].get(i).getId()!=id){
            //     break;
            // }
            if (sectors[id%10].get(i).getStock()>amount){
                if (sectors[id%10].get(i).getId()==id){
                    sectors[id%10].get(i).setLastPurchaseDay(day);
                    sectors[id%10].get(i).updateStock(-amount);
                    sectors[id%10].get(i).updateDemand(amount);
                    sectors[id%10].sink(i);
                    break;
                }
            }
        }
    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD
        // for (int i =1; i<=sectors[id%10].getSize(); i++){

            int cg = 0;
            int idf = id;
            if (sectors[id % 10].getSize() == 5) {
                int i = ((id % 10) + 1) % 10;
                while (i != id % 10) {
                    if (sectors[i].getSize() < 5) {
                        sectors[i].add(new Product    (id, name, stock, day, demand));
                        // int n = sectors[i].getSize();
                        sectors[i].swim(sectors[i].getSize());
                        break;
                    }
                    i = (i + 1) % 10;
                    cg++;
                    cg += i;
                }
                if (i == idf % 10) {
                    evictIfNeeded(idf);
                    addToEnd(idf, name, stock, day, demand);
                    fixHeap(idf);
                }
            } 
            
            else if (sectors[id % 10].getSize() < 5) {
                addToEnd(idf, name, stock, day, demand);
                fixHeap(idf);
            }
        }

            
            //if sector is not full just add like normal:
            // if (sectors[id%10].get(i).getStock()){
            //     sectors[id%10].add(new Product(id, name, stock, day, demand));
            // // }
            // // // if there is an open sector:
            // if (sectors[id%10].get(i).getStock()){
            //     sectors[id%10].add(new Product(id, name, stock, day, demand));
            // }

            // // }
            // // if there are no open sectors:
            // // if (sectors[id%10].get(i).getStock()){
            //     sectors[id%10].add(new Product(id, name, stock, day, demand));
            // }
        // }


    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}
