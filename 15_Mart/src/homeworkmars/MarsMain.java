package homeworkmars;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MarsMain {

	// Toplam Satır Sayısı-->50
	// Toplam Kelime Sayısı--> 273
	// toplam Karakter Sayısı--> 1593
	// En çok tekrar eden kelime--> arkadaş

	public static void satirSayisi() {
		int satirSayisi = 0;
		int kelimeSayisi = 0;
		int karakterSayisi = 0;

		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("mars.txt")))) {

			while (scanner.hasNextLine()) {
				String satir = scanner.nextLine();
				satirSayisi++;

				String array[] = satir.toLowerCase().split(" ");
				kelimeSayisi += array.length;

				for (int i = 0; i < array.length; i++) {
					karakterSayisi += array[i].length();
				}
			}

			System.out.println("Toplam satır sayısı: " + satirSayisi);
			System.out.println("Toplam kelime sayısı: " + kelimeSayisi);
			System.out.println("Toplam karakter sayısı: " + karakterSayisi);

		} catch (Exception e) {

		}
	}

	static Set<Character> harf = new HashSet<>();
	static Map<String, Integer> kelimeveAdet = new HashMap<>();

	public static int kelimeTekrarSayisi() {
		int kelimeSayisi = 0;
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("mars.txt")))) {

			while (scanner.hasNextLine()) {
				String okunanSatir = scanner.nextLine().toLowerCase();
				String array[] = okunanSatir.split(" ");
				for (String kelime2 : array) {
					String geciciKelime = kelime2;
					char[] chars = kelime2.toCharArray();
					try {
						if (chars.length == 1 && chars[0] == 'o') {
							geciciKelime = "o";
						} else if (!harf.contains((char) chars[0])) {
							geciciKelime = geciciKelime.substring(1);
						} else if (!harf.contains((char) chars[chars.length - 1])) {
							geciciKelime = geciciKelime.substring(0, chars.length - 1);
						}

						else if (!harf.contains((char) chars[chars.length - 2])) {
							geciciKelime = geciciKelime.substring(0, chars.length - 1);
						}
						if (kelimeveAdet.containsKey(geciciKelime)) {
							kelimeveAdet.put(geciciKelime, kelimeveAdet.get(geciciKelime) + 1);

						} else {
							kelimeveAdet.put(geciciKelime, 1);

						}
					} catch (IndexOutOfBoundsException e) {

					}
				}
			}
			System.out.println("\n");
		} catch (FileNotFoundException e) {
			System.out.println("Dosya bulunamadı!");
		}
		return kelimeSayisi;
	}

	public static void harfleriDoldur() {
		for (int i = 65; i < 91; i++) {
			harf.add((char) i);
		}
		for (int i = 97; i < 123; i++) {
			harf.add((char) i);
		}
		harf.add((char) 304);
		harf.add((char) 305);
		harf.add((char) 214);
		harf.add((char) 246);
		harf.add((char) 220);
		harf.add((char) 252);
		harf.add((char) 199);
		harf.add((char) 231);
		harf.add((char) 286);
		harf.add((char) 287);
		harf.add((char) 350);
		harf.add((char) 351);
	}

	public static void main(String[] args) {

		satirSayisi();
		harfleriDoldur();
		kelimeTekrarSayisi();

		String tekrarliKelime = kelimeveAdet.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

		int tekrarEdenKelime = kelimeveAdet.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();

		System.out.println("En çok tekrar eden kelime, " + tekrarliKelime + " kelimesidir " + tekrarEdenKelime
				+ " kez tekrar etmiştir.");
		System.out.println("\n");
		System.out.println("Kalan kelimeler aşağıda belirtilen sayıda tekrar etmiştir: ");
		System.out.println(kelimeveAdet);

	}

}