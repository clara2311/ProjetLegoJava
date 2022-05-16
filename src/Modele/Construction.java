package Modele;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Construction {

	private HashMap<TypeBrique, ArrayList<Brique>> types = new HashMap<>();

	private ArrayList<Brique> briques = new ArrayList<>();

	public Construction() {
	}

	
	public Construction(String chemin) throws IOException {
		Scanner sc = new Scanner(new File(chemin));
		Pattern typeP = Pattern.compile("\\d+;\\d+;\\d+;\\p{XDigit}*");
		Pattern briqueP = Pattern.compile("-?\\d+;-?\\d+;-?\\d+");
		String t[];
		while (sc.hasNext(typeP)) {
			t = sc.next(typeP).split(";");
			TypeBrique tb = new TypeBrique(Integer.parseInt(t[0]),
					Integer.parseInt(t[1]),
					Integer.parseInt(t[2]),
					new Color(Integer.parseInt(t[3], 16)));
			types.put(tb,
					new ArrayList<>());
			while (sc.hasNext(briqueP)) {
				t = sc.next(briqueP).split(";");
				Brique b = new Brique(tb,
						Integer.parseInt(t[0]),
						Integer.parseInt(t[1]),
						Integer.parseInt(t[2]));
				briques.add(b);
				types.get(tb).add(b);
			}
        }
	}
	
	public void sauvegarder(String chemin) throws IOException {
			FileWriter f = new FileWriter(chemin);
			for (TypeBrique t : types.keySet()) {
				f.write(String.format("%d;%d;%d;%02x%02x%02x\n", t.largeur, t.longueur, t.hauteur,
						t.couleur.getRed(), t.couleur.getGreen(), t.couleur.getBlue()));
				f.write('\n');
				for (Brique b : types.get(t)) {
					f.write(String.format("%d;%d;%d\n", b.x, b.y, b.z));
				}
				f.write('\n');
			}
			f.close();
	}
	
	public void ajouter(Brique brique) {
		if (!types.containsKey(brique.type)) {
			types.put(brique.type, new ArrayList<>());
		}
		briques.add(brique);
		types.get(brique.type).add(brique);
	}
	
	public static void main(String[] args) throws IOException {
		Construction c = new Construction("fichierDonnées.lego");
		/*Construction c = new Construction();
		c.ajouter(new Brique(new TypeBrique(67, 3, 2, new Color(26, 26, 26)),
				10, 10, 10));

		c.ajouter(new Brique(new TypeBrique(1, 1, 1, new Color(255,0,0)),
				0, 0, -10));
		c.sauvegarder("fichierDonnées.lego");
		*/
	}
}

