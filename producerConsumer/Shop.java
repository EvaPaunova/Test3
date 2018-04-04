package producerConsumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Shop {
	
	//product name -> quantity
	private ConcurrentHashMap<String, Integer> products = new ConcurrentHashMap<>();
	
	Shop(){
		products.put("Orange", 23);
		products.put("Banana", 17);
		products.put("Apple", 18);
	}

	public synchronized void buy(String product, int quantity) {
		while(products.get(product) < 5) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("ops");
			}
		}
		int initialQuantity = products.get(product);
		products.put(product, products.get(product)-quantity);
		System.out.println(product + "s are now " + initialQuantity + ".Client bought " + product + ". Left = " + products.get(product));
		notifyAll();
	}
	
	public synchronized void deliver() {
		
		while(!hasDeficit()) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("ops");
			}
		}
		for(Map.Entry<String, Integer> e : products.entrySet()) {
			if(e.getValue() < 5) {
				products.put(e.getKey(), products.get(e.getKey())+20);
				System.out.println("Supplier delivered " + e.getKey() + ". Left = " + products.get(e.getKey()));
			}
		}
		notifyAll();
	}
	
	private boolean hasDeficit() {
		boolean deficit = false;
		for(Map.Entry<String, Integer> e : products.entrySet()) {
			if(e.getValue() < 5) {
				deficit = true;
				break;
			}
		}
		return deficit;
	}
	
	public List<String> getProductNames(){
		return new ArrayList<>(products.keySet());
	}
}
