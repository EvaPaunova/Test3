package gas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;

public class Cashier extends Thread{

	static GasStation station;
	private ArrayBlockingQueue<Car> kasa;
	
	public Cashier(ArrayBlockingQueue<Car> kasa) {
		this.kasa = kasa;
	}
	
	@Override
	public void run() {
		while (true){
			try {
				Car car = kasa.take();
				Thread.sleep(5000);
				station.addData(car.getFuel(), car.getAmount(), car.getKolonka(), LocalDateTime.now());
				//add to common collection 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
