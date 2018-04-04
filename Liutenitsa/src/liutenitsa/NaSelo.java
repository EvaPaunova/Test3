package liutenitsa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class NaSelo {
	
	private ConcurrentHashMap<String, Integer> baskets = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, Integer> tavi = new ConcurrentHashMap<>();
	
	public NaSelo() {
		
		baskets.put("Domat", 10);
		baskets.put("Patl", 10);
		baskets.put("Chushka", 10);
		
		tavi.put("Domat", 10);
		tavi.put("Patl", 10);
		tavi.put("Chushka", 10);
		Baba.selo = this;
		Moma.selo = this;
		Momak.selo = this;
	}

	public ArrayList<String> getProductNames(){
		return new ArrayList<>(baskets.keySet());
	}

	public void addToBasket(String product, int quantity) {
		synchronized (baskets) {
			while(baskets.get(product) + quantity > 40) {
				try {
					baskets.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.print("Moma nabra " + product + ": " + baskets.get(product) + " - ");
			baskets.put(product, baskets.get(product) + quantity);
			System.out.println(baskets.get(product));
			baskets.notifyAll();
		}
			
	}

	public void momakWorkBasket(String product) {
		synchronized (baskets) {
			while(baskets.get(product)<1) {
				try {
					baskets.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			baskets.put(product, baskets.get(product) - 1);
			System.out.println("Momak vze " + product + ": " + baskets.get(product));
			baskets.notifyAll();
		}
		
	}
	
	public void momakWorkTavi(String product) {
		synchronized (tavi) {
			while(tavi.get(product) >= 30) {
				try {
					tavi.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			tavi.put(product, tavi.get(product) + 1);
			System.out.println("Momak sloji " + product + ": " + tavi.get(product));
			tavi.notifyAll();
		}	
		
	}

	public void makeLiutenitsa() {
		synchronized (tavi) {
			while(tavi.get("Domat") < 5 || tavi.get("Patl") < 5 ||tavi.get("Chushka") < 5) {
			try {
				tavi.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			for(String product: tavi.keySet()) {
				tavi.put(product, tavi.get(product) - 5);
			}
			System.out.println("Baba make lutenica");
			tavi.notifyAll();
		}
	}
}
