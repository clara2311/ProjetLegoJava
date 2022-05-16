package Modele;
public class Brique {

	public TypeBrique type;
	public int x;
	public int y;
	public int z;

	public Brique(TypeBrique type, int x, int y, int z) {
		if (type == null)
			throw new IllegalArgumentException("Le type de la Brique doit être non-null");
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean touche(Brique o) {
		return (this.x <= o.x ? this.x + this.type.largeur > o.x : o.x + o.type.largeur > this.x)
				&& (this.y <= o.y ? this.y + this.type.largeur > o.y : o.y + o.type.largeur > this.y)
				&& (this.z <= o.z ? this.z + this.type.largeur > o.z : o.z + o.type.largeur > this.z);
	}
	
	@Override
	public String toString() {
		return String.format("%s %d %d %d", type.toString(), x, y, z);
	}

}
