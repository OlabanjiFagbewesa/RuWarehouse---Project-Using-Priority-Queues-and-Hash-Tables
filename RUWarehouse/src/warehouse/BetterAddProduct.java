package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse nWarehouse = new Warehouse();
        int n = StdIn.readInt(); // represents the N lines of code.
        for (int i=0; i<n; i++){
            int day = StdIn.readInt(); // representing the day through an int (day of the item to add.)
            int id = StdIn.readInt(); // represents the id number of the item to add.
            String name = StdIn.readString(); // name of the item to add (guaranteed to not contain spaces).
            int stock = StdIn.readInt(); // represents the initial item stock to be added.
            int demand = StdIn.readInt(); // represents the intitial item demand.
            nWarehouse.betterAddProduct(id, name, stock, day, demand);
        }
        StdOut.print(nWarehouse);
        
        // Use this file to test betterAddProduct
    }
}
