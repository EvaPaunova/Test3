package producerConsumer;

import java.util.List;
import java.util.Random;

public class Supplier extends Thread {

	public static Shop shop;
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("ops");
			}
			shop.deliver();
		}
		
	}
}