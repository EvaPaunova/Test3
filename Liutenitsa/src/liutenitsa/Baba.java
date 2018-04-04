package liutenitsa;

import java.time.LocalDate;
import java.util.Random;

public class Baba extends Thread{

	public static NaSelo selo;
	private static volatile int liutenitsa_id = 1;
	
	private String firstName;
	private int age;
	
	public Baba(String name) {
		this.firstName = name;
		this.age = new Random().nextInt(6) + 60;
	}

	public String firstName() {
		return firstName;
	}

	public int getAge() {
		return age;
	}
	
	@Override
	public void run() {
		while (true) {
			selo.makeLiutenitsa();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBManager.getInstance().insertLiutenitsa(liutenitsa_id, LocalDate.now().toString(), new Random().nextInt(10) + 3, this.firstName);
			liutenitsa_id = liutenitsa_id + 1;
		}
	}
	
}
