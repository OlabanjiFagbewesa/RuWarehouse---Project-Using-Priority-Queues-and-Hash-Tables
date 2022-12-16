package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse warehouseObj1 = new Warehouse();
        int n = StdIn.readInt(); // represents number of queries (13).
        for (int i =0; i < n ; i++){
            String reader = StdIn.readString();
            if (reader.equals("add")){
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String productName = StdIn.readString();
                int initialItemStock = StdIn.readInt();
                int initialItemDemand = StdIn.readInt();
                warehouseObj1.addProduct(id, productName, initialItemStock, day, initialItemDemand);
            }
            else{
                int day = StdIn.readInt();
                int productIdToPurchase = StdIn.readInt();
                int amountPurchased = StdIn.readInt();
                warehouseObj1.purchaseProduct(productIdToPurchase, day, amountPurchased);
            }
        }
        StdOut.println(warehouseObj1);


	// Use this file to test purchaseProduct
    }
}
