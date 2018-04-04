package gas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import gas.Car.FuelType;

public class GasStation {
	
	private Cashier penka;
	private Cashier minka;
	private Boy pesho = new Boy();
	private Boy gosho = new Boy();
	
	private ArrayList<Queue<Car>> kolonki = new ArrayList<>();
	private ArrayList<ArrayBlockingQueue<Car>> kasi = new ArrayList<>();
	
	private ConcurrentHashMap<Integer, HashMap<FuelType, ConcurrentHashMap<LocalDateTime, Integer>>> statistics;
	
	public GasStation() {
		statistics = new ConcurrentHashMap<>();
		for(int i = 0; i < 5; i++) {
			kolonki.add(new LinkedList<>());
			statistics.put(i+1, new HashMap<>());
			statistics.get(i+1).put(Car.FuelType.GAS, new ConcurrentHashMap<>());
			statistics.get(i+1).put(Car.FuelType.DIESEL, new ConcurrentHashMap<>());
			statistics.get(i+1).put(Car.FuelType.PETROL, new ConcurrentHashMap<>());
		}
		for(int i = 0; i < 2; i++) {
			kasi.add(new ArrayBlockingQueue<>(15));
		}
		Boy.station = this;
		Cashier.station = this;
		penka = new Cashier(kasi.get(0));
		minka = new Cashier(kasi.get(1));
		pesho.start();
		gosho.start();
		penka.start();
		minka.start();

	}
	
	public void enterGasStation(Car c) throws InterruptedException {
		kolonki.get(new Random().nextInt(kolonki.size())).offer(c);
	}
	
	public List<Queue<Car>> getKolonki(){
		return Collections.unmodifiableList(kolonki);
	}

	public void alignToPay(Car car) {
		kasi.get(new Random().nextInt(kasi.size())).offer(car);
	}
	
	public void addData(FuelType fuel, int amount, int kolonka, LocalDateTime date) {
		statistics.get(kolonka).get(fuel).put(date, amount);
		DBManager.getInstance().insertIntoDB(fuel, amount, kolonka, date);
	}

}
