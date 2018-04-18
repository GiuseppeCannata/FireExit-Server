package entity;

import java.util.Comparator;


public class PesoArco {

	private int id;
	private int idArco;
	private Peso peso;
	private int valore;

	public PesoArco(int id, int idArco, Peso peso, int valore) {


		this.id = id;
		this.idArco = idArco;
		this.peso = peso;
		this.valore = valore;

	}

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}

	public int getIdArco() {
		return idArco;
	}
	public void setIdArco(int idArco) {
		this.idArco = idArco;
	}

	public Peso getPeso() {
		return peso;
	}
	public void setPeso(Peso peso) {
		this.peso = peso;
	}

	public int getValore() {
		return valore;
	}
	public void setValore(int valore) {
		this.valore = valore;
	}


	/*Comparator for sorting the list by roll no*/
	public static Comparator<PesoArco> PAIdArco = new Comparator<PesoArco>() {

		public int compare(PesoArco pa1, PesoArco pa2) {

			int idA1 = pa1.getIdArco();
			int idA2 = pa2.getIdArco();

			/*For ascending order*/
			return idA1-idA2;

			/*For descending order*/
			//rollno2-rollno1;
		}};

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			PesoArco pesoArco = (PesoArco) o;

			if (valore != pesoArco.valore) return false;
			return peso.equals(pesoArco.peso);
		}
}
