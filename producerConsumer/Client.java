package producerConsumer;

import java.util.List;
import java.util.Random;

public class Client extends Thread {

	public static Shop shop;
	
	@Override
	public void run() {
		while(true) {
			List<String> products = shop.getProductNames();
			Random r = new Random();
			shop.buy(products.get(r.nextInt(products.size())), r.nextInt(3)+1);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("ops");
			}
		}
		
	}
}
