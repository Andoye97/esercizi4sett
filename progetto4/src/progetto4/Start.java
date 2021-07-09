package progetto4;

import java.sql.SQLException;
import java.util.Optional;

public class Start {

	public static void main(String[] args) {

		HandConnection hc = new HandConnection("postgresql", "5432", "esercitazione4", "negozio", "postgres",
				"Epicode");

		FornitoreDAO fr = new FornitoreDAO(hc);
		ProdottoDAO pr = new ProdottoDAO(hc);

		Fornitore f1 = new Fornitore("bbb", "nike", "via bianchi 3", "roma");
		Fornitore f2 = new Fornitore("vvv", "apple", "via rossi 3", "roma");
		Fornitore f3 = new Fornitore("bbb", "sony", "via gianni 3", "bologna");
		Fornitore f4 = new Fornitore("aaa", "nespresso", "via roma 2", "roma");
		Fornitore f5 = new Fornitore("vbn", "nike", "via rini 8", "pavia");

		try {

			Optional<Fornitore> of = fr.getFornitore("aaa"); // "salviamo" il risultato del metodo nella variabile of di tipo Optional<Fornitore>

			if (of.isPresent()) { //condizione che verifica che Optional non sia empty 

				Fornitore x = of.get(); // bisogna chiamare il metodo get() dopo che abbiamo controllato che ci sia qualcosa (togliamo Optional nella stampa)
				                        // "salviamo" il risultato nella variabile x di tipo Fornitore
				if (x.getIndirizzo().length() > 5) { // condizione che verifica che la lunghezza dell'indirizzo del fornitore sia maggiore di 5
					System.out.println(x.getNome()); // stampa il nome del fornitore  
				}
			}
			System.out.println(fr.getAllFornitore());
			fr.save(f5);
			fr.update(f1);
			fr.delete("zzz");
			System.out.println(fr.getFornitorePerCitta("roma"));
			System.out.println(pr.getProdottoPerFornitore(f5));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
