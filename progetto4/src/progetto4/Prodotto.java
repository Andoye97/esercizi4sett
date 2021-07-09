package progetto4;

public class Prodotto {

	private String codiceProdotto;
	private String nome;
	private String descrizione;
	private String marca;
	private int prezzo;
	private Fornitore f;

	public Prodotto(String codiceProdotto, String nome, String descrizione, String marca, int prezzo, Fornitore f) {
		this.codiceProdotto = codiceProdotto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.marca = marca;
		this.prezzo = prezzo;
		this.f = f;
	}

	public Prodotto(String codiceProdotto, String nome, String descrizione, String marca, int prezzo) {
		this.codiceProdotto = codiceProdotto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.marca = marca;
		this.prezzo = prezzo;
	}

	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getMarca() {
		return marca;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public Fornitore getF() {
		return f;
	}

	@Override
	public String toString() {
		return "Prodotto codiceProdotto=" + codiceProdotto + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", marca=" + marca + ", prezzo=" + prezzo;
	}
}
