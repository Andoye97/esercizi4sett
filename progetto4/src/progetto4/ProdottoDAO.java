package progetto4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

	HandConnection c1 = new HandConnection("postgresql", "5432", "esercitazione4", "negozio", "postgres", "Epicode");
	
	public ProdottoDAO(HandConnection c1) {
		this.c1 = c1;
	}

	
	private static final String QUERY_GET_LISTAPROD = "SELECT p.codiceprodotto, p.nome, p.descrizione, p.marca , p.prezzo "
			+ "FROM negozio.prodotti p  INNER JOIN negozio.fornitore f on f.codicefornitore = p.codice_fornitore "
			+ "WHERE f.codicefornitore = ?";

	public List<Prodotto> getProdottoPerFornitore(Fornitore f) throws SQLException {

		List<Prodotto> listaProd = new ArrayList<Prodotto>();

		try (Connection con = c1.gettConnection(); PreparedStatement pst = con.prepareStatement(QUERY_GET_LISTAPROD)) {

			pst.setString(1, f.getCodiceFornitore());

			try (ResultSet result = pst.executeQuery()) {

				while (result.next()) {

					listaProd.add(new Prodotto(result.getString("codiceprodotto"), result.getString("nome"),
							result.getString("descrizione"), result.getString("marca"), result.getInt("prezzo")));
				}

				return listaProd;
			}
		}
	}
}
