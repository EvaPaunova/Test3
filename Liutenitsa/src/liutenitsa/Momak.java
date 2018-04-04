package liutenitsa;

import java.util.Random;

public class Momak extends Thread{

	public static NaSelo selo;
	
	private String firstName;
	private int age;
	
	public Momak(String name) {
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
			selo.momakWorkBasket(product);
			try {
				if(product.equals("Domat")) {
					Thread.sleep(3000);
				}
				else if(product.equals("Patl")) {
					Thread.sleep(9000);
				}
				else {
					Thread.sleep(6000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			selo.momakWorkTavi(product);
		}
	}
	
}
