package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        //warehouse obj:

        Warehouse warehouseObj = new Warehouse();

        int n = StdIn.readInt(); // integer n representing the number of queries.

        // below is n lines:
        // each containing either an add query or a restock query:

        //add query:
        for (int i =0; i<n; i++){
            String reader = StdIn.readString();
            if (reader.equals("add")){
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String productName = StdIn.readString();
                int initialItemStock = StdIn.readInt();
                int initialItemDemand = StdIn.readInt();
                warehouseObj.addProduct(id, productName, initialItemStock, day, initialItemDemand);

            }
            // restock query:
            else{
                int productId = StdIn.readInt();
                int amount = StdIn.readInt();
                warehouseObj.restockProduct(productId, amount);
            }
        }

	// Use this file to test restock
    StdOut.println(warehouseObj);
    }
}
