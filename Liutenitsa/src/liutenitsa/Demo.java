package liutenitsa;

public class Demo {
	
	public static void main(String[] args) {
		NaSelo selo = new NaSelo();
		Baba baba1 = new Baba("Baba 1");
		Moma moma1 = new Moma("Moma 1");
		Momak momak1 = new Momak("Momak 1");
		Baba baba2 = new Baba("Baba 2");
		Moma moma2 = new Moma("Moma 2");
		Momak momak2 = new Momak("Momak 2");
		baba1.start();
		moma1.start();
		momak1.start();
		baba2.start();
		moma2.start();
		momak2.start();
	}

}
