package progetto4;

public class Fornitore {

	private String codiceFornitore;
	private String nome;
	private String indirizzo;
	private String città;

	public Fornitore(String codiceFornitore, String nome, String indirizzo, String città) {
		this.codiceFornitore = codiceFornitore;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.città = città;
	}

	public String getCodiceFornitore() {
		return codiceFornitore;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getCittà() {
		return città;
	}

	@Override
	public String toString() {
		return "Fornitore codiceFornitore=" + codiceFornitore + ", nome=" + nome + ", indirizzo=" + indirizzo
				+ ", città=" + città;
	}

}
