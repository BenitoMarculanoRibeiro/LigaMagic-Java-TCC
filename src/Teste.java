import java.util.ArrayList;

public class Teste {
	public static void main(String[] args) {
		ArrayList<Long> vet = new ArrayList<Long>();
		vet.add((long) 10);
		vet.add((long) 100);
		vet.add((long) 1000);
		vet.add((long) 10000);
		vet.add((long) 100000);
		vet.add((long) 1000000);
		vet.add((long) 10000000);
		vet.add((long) 100000000);
		vet.add((long) 1000000000);
		for (Long k : vet) {
			long t1 = System.currentTimeMillis();
			for (int j = 0; j < 10; j++) {
				long i = 0;
				for (i = 0; i < k; i++) {
				}
			}
			long t2 = System.currentTimeMillis();
			long total = t2 - t1;
			System.out.println("Tamanho: " + k + " Media Milisegundos: " + total / 10);
		}
	}
}
