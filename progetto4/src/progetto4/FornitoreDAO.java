package progetto4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornitoreDAO {

	HandConnection c1 = new HandConnection("postgresql", "5432", "esercitazione4", "negozio", "postgres", "Epicode");

	public FornitoreDAO(HandConnection c1) {
		this.c1 = c1;
	}

	private static final String QUERY_GET_FORNITORE = "SELECT f.codicefornitore, f.nome, f.indirizzo, f.città FROM negozio.fornitore f WHERE f.codicefornitore =?";

	private static final String QUERY_GET_ALL_FORNITORE = "SELECT f.codicefornitore, f.nome, f.indirizzo, f.città FROM negozio.fornitore f";

	private static final String QUERY_SAVE = "INSERT INTO negozio.fornitore (codicefornitore, nome, indirizzo, città)"
			+ "VALUES (?,?,?,?)";

	private static final String QUERY_UPDATE = "UPDATE negozio.fornitore SET nome=?, indirizzo=?, città=? WHERE codicefornitore=?";
	private static final String QUERY_DELETE_PRODOTTO_PER_FORNITORE = "DELETE FROM negozio.prodotti WHERE codice_fornitore=?";

	private static final String QUERY_DELETE_FORNITORE = "DELETE FROM negozio.fornitore WHERE codicefornitore=?";

	private static final String QUERY_LISTA_CITTA = "SELECT codicefornitore, nome, indirizzo, città FROM negozio.fornitore WHERE città=?";

	public Optional<Fornitore> getFornitore(String codiceFornitore) throws SQLException {
		Fornitore f = null;
		try (Connection con = c1.gettConnection(); PreparedStatement pst = con.prepareStatement(QUERY_GET_FORNITORE)) {

			pst.setString(1, codiceFornitore);

			try (ResultSet result = pst.executeQuery()) {

				if (result.next()) {

					f = new Fornitore(result.getString("codiceFornitore"), result.getString("nome"),
							result.getString("indirizzo"), result.getString("città"));
					return Optional.of(f);
					
				}
				return Optional.empty();
			}
		}

	}

	public List<Fornitore> getAllFornitore() throws SQLException {

		List<Fornitore> allList = new ArrayList<Fornitore>();

		try (Connection con = c1.gettConnection();
				Statement st = con.createStatement();
				ResultSet result = st.executeQuery(QUERY_GET_ALL_FORNITORE)) {

			while (result.next()) {

				allList.add(new Fornitore(result.getString("codiceFornitore"), result.getString("nome"),
						result.getString("indirizzo"), result.getString("città")));
			}

			return allList;
		}
	}

	public void save(Fornitore f) throws SQLException {

		try (Connection con = c1.gettConnection(); PreparedStatement pst = con.prepareStatement(QUERY_SAVE)) {

			pst.setString(1, f.getCodiceFornitore());
			pst.setString(2, f.getNome());
			pst.setString(3, f.getIndirizzo());
			pst.setString(4, f.getCittà());

			pst.executeUpdate();

		}
	}

	public void update(Fornitore f) throws SQLException {
		try (Connection con = c1.gettConnection(); PreparedStatement pst = con.prepareStatement(QUERY_UPDATE)) {

			pst.setString(1, f.getNome());
			pst.setString(2, f.getIndirizzo());
			pst.setString(3, f.getCittà());
			pst.setString(4, f.getCodiceFornitore());

			pst.executeUpdate();

		}
	}

	public void delete(String codiceFornitore) throws SQLException {
		try (Connection con = c1.gettConnection();
				PreparedStatement pst1 = con.prepareStatement(QUERY_DELETE_PRODOTTO_PER_FORNITORE);
				PreparedStatement pst2 = con.prepareStatement(QUERY_DELETE_FORNITORE)) {

			pst1.setString(1, codiceFornitore);
			pst2.setString(1, codiceFornitore);
			try {                          // metto questo try dentro il try with resources per poter chiamare "con.rollback" in linea 110
				con.setAutoCommit(false); //toglie il commit di default 
				pst1.executeUpdate();
				pst2.executeUpdate();
				con.commit(); //faccio il commit dopo tutti gli update (transazioni)	
			} catch (SQLException e ) {	 // il catch prende le eccezioni che si possono verificare da linea 105 a linea 108
				con.rollback(); // se si verifica l'eccezione faccio il rollback
				throw e; //rilancio l'eccezione che ho preso
			}
		} 
	}

	public List<Fornitore> getFornitorePerCitta(String città) throws SQLException {

		List<Fornitore> listaForCit = new ArrayList<Fornitore>();

		try (Connection con = c1.gettConnection(); PreparedStatement pst = con.prepareStatement(QUERY_LISTA_CITTA)) {

			pst.setString(1, città);

			try (ResultSet result = pst.executeQuery()) {

				while (result.next()) {

					listaForCit.add(new Fornitore(result.getString("codiceFornitore"), result.getString("nome"),
							result.getString("indirizzo"), result.getString("città")));

				}

				return listaForCit;
			}

		}

	}
}
