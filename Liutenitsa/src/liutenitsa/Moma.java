package liutenitsa;

import java.util.Random;

public class Moma extends Thread{
	
	public static NaSelo selo;
	
	private String firstName;
	private int age;
	
	public Moma(String name) {
		this.firstName = name;
		this.age = new Random().nextInt(6) +14;
	}

	public String firstName() {
		return firstName;
	}

	public int getAge() {
		return age;
	}
	
	@Override
	public void run() {
		while(true) {
			String product = selo.getProductNames().get(new Random().nextInt(selo.getProductNames().size()));
			int quantity = new Random().nextInt(4)+3;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			selo.addToBasket(product, quantity);
		}
	}

}
