package gas;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import gas.Car.FuelType;

public class Boy extends Thread{
	
	static GasStation station;

	@Override
	public void run() {
		while(true) {
			for(int i = 0; i < station.getKolonki().size(); i++) {
				Queue<Car> kolonka = station.getKolonki().get(i);
				Car car;
				synchronized (kolonka) {
					if(kolonka.isEmpty()) {
						continue;
					}
					car = kolonka.poll();
				}
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FuelType fuelType = Car.FuelType.values()[new Random().nextInt(Car.FuelType.values().length)];
				car.setFuel(fuelType);
				car.setAmount(new Random().nextInt(31)+10);
				car.setKolonka(i + 1);
				station.alignToPay(car);
			}
		}
	}
	
}
