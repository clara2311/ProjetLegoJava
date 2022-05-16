package Modele;
import java.awt.Color;
import java.util.Objects;

public class TypeBrique {

	public final int largeur;
	public final int longueur;
	public final int hauteur;
	public final Color couleur;

	public TypeBrique(int largeur, int longueur, int hauteur, Color couleur) {
		if (largeur <= 0 || longueur <= 0 || hauteur <= 0)
			throw new IllegalArgumentException("La taille du TypeBrique doit être strictement positive");
		if (couleur == null)
			throw new IllegalArgumentException("La couleur du TypeBrique doit être non-null");
		this.largeur = largeur;
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.couleur = couleur;
	}

	
	@Override
	public String toString() {
		return String.format("%d %d %d %02x%02x%02x", largeur, longueur, hauteur,
				couleur.getRed(), couleur.getGreen(), couleur.getBlue());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		TypeBrique other = (TypeBrique) o;
		return this.largeur == other.largeur &&
				this.longueur == other.longueur &&
				this.hauteur == other.hauteur &&
				this.couleur.equals(other.couleur);
	}

	@Override
	public int hashCode() {
		return Objects.hash(largeur, longueur, hauteur, couleur);
	}

}
