package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse warehouseobj = new Warehouse();
        int n = StdIn.readInt(); // number of queries
        for (int i =0; i<n; i++){
            String reader = StdIn.readString();
            if (reader.equals("add")){
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String productName = StdIn.readString();
                int initialItemStock = StdIn.readInt();
                int initialItemDemand = StdIn.readInt();
                warehouseobj.addProduct(id, productName, initialItemStock, day, initialItemDemand);
            }
            else{
                int id = StdIn.readInt(); // the product ID 
                warehouseobj.deleteProduct(id);
            }
        }

	// Use this file to test deleteProduct
    StdOut.print(warehouseobj);
    }
}
