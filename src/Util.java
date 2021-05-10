import java.util.Scanner;

public class Util {
	static Scanner entrada;

	public static void p(String texto) {
		System.out.print(texto + "\n");
	}

	public static String exibeVet(int[] vet) {
		String saida = "";
		for (int i = 0; i < vet.length; i++) {
			if (vet[i] < 10 && vet[i] >= 0) {
				saida += "|0" + vet[i];
			} else {
				saida += "|" + vet[i];
			}
		}
		saida += "|";
		return saida;
	}

	public static void insertionSort(float[] vetor) {
		int j;
		float key;
		int i;

		for (j = 1; j < vetor.length; j++) {
			key = vetor[j];
			for (i = j - 1; (i >= 0) && (vetor[i] > key); i--) {
				vetor[i + 1] = vetor[i];
			}
			vetor[i + 1] = key;
		}
	}

	public static String exibeVet(float[] vet) {
		String saida = "";
		for (int i = 0; i < vet.length; i++) {
			if (vet[i] < 10 && vet[i] >= 0) {
				saida += "|0" + vet[i];
			} else {
				saida += "|" + vet[i];
			}
		}
		saida += "|";
		return saida;
	}

	public static String exibeVet(String[] vet) {
		String saida = "";
		for (int i = 0; i < vet.length; i++) {
			saida += "|" + vet[i];
		}
		saida += "|";
		return saida;
	}

	public static String leString(String texto) {
		entrada = new Scanner(System.in);
		System.out.print(texto);
		return entrada.nextLine();
	}

	public static String exibeMat(int[][] mat) {
		String saida = "";
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] < 10) {
					saida += "|0" + mat[i][j];
				} else {
					saida += "|" + mat[i][j];
				}
			}
			saida += "|\n";
		}
		return saida;
	}

	public static String exibeMat(float[][] mat) {
		String saida = "";
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] < 10) {
					saida += "|0" + mat[i][j];
				} else {
					saida += "|" + mat[i][j];
				}
			}
			saida += "|\n";
		}
		return saida;
	}

	public static int leInt(String texto) {
		entrada = new Scanner(System.in);
		boolean cond = false;
		int num = 0;
		do {
			try {
				System.out.print(texto);
				String temp = entrada.nextLine();
				num = Integer.parseInt(temp);
				cond = true;
			} catch (Exception e) {
				System.out.print("\nInforme um valor inteiro ANIMAL!!!\n");
			}
		} while (!cond);
		return num;
	}

	public static double lePositivo(String texto) {
		double num = 0;
		do {
			num = leDouble(texto);
			if (num < 0) {
				System.out.print("\nInforme um valor Positivo!\n");
			}
		} while (num < 0);
		return num;
	}

	public static double leDouble(String texto) {
		entrada = new Scanner(System.in);
		boolean cond = false;
		double num = 0;
		do {
			try {
				System.out.print(texto);
				String temp = entrada.nextLine();
				num = Double.parseDouble(temp);
				cond = true;
			} catch (Exception e) {
				System.out.print("\nInforme um valor inteiro ANIMAL!!!\n");
			}
		} while (!cond);
		return num;
	}

	public static double leIntervalo(String texto, double min, double max) {
		entrada = new Scanner(System.in);
		double num = 0;
		do {
			num = Util.leDouble(texto);
			if (num < min || num > max) {
				System.out.print("\nInforme um valor entre " + min + " e " + max + "!\n");
			}
		} while (num < min || num > max);
		return num;
	}

	public static char leChar(String texto) {
		entrada = new Scanner(System.in);
		String temp;
		do {
			System.out.print(texto);
			temp = entrada.nextLine();
			if (temp.length() > 1) {
				System.out.print("\nInforme apenas 1 caracter...\n");
			}
		} while (temp.length() > 1);
		return temp.charAt(0);
	}

	public static int[] criaVetRand(int tam, int min, int max) {
		if (min > max || tam <= 0) {
			return null;
		} else {
			int[] vet = new int[tam];
			for (int i = 0; i < tam; i++) {
				vet[i] = (int) (Math.random() * (max - min + 1) + min);
			}
			return vet;
		}
	}

	public static int[][] criaMatRand(int lin, int col, int min, int max) {
		if (min > max || lin <= 0 || col <= 0) {
			return null;
		} else {
			int[][] mat = new int[lin][col];
			for (int i = 0; i < lin; i++) {
				for (int j = 0; j < col; j++) {
					mat[i][j] = (int) (Math.random() * (max - min + 1) + min);
				}
			}
			return mat;
		}
	}

	public static void limpaTela() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	public static void pausa() {
		leString("Pressiona qualquer tecla para continuar...");
	}
}
