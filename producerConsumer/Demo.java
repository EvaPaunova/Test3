package producerConsumer;

public class Demo {

	public static void main(String[] args) {
		
		Shop s = new Shop();
		Client.shop = s;
		Supplier.shop = s;
		int clients = 10;
		for(int i = 0; i < clients; i++) {
			new Client().start();
		}
		int suppliers = 10;
		for(int i = 0; i < suppliers; i++) {
			new Supplier().start();
		}
	}
}
