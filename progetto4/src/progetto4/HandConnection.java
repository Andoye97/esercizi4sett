package progetto4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HandConnection {

	static {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			// System.exit(1);
			throw new IllegalStateException("Driver postgres mancante " + e.getMessage());
		}

	}

	private String tipoDatabase;
	private String indirizzoPorta;
	private String nomeDatabase;
	private String nomeSchema;
	private String userName;
	private String password;
	private Connection con;

	public HandConnection(String tipoDatabase, String indirizzoPorta, String nomeDatabase, String nomeSchema,
			String userName, String password) {
		this.tipoDatabase = tipoDatabase;
		this.indirizzoPorta = indirizzoPorta;
		this.nomeDatabase = nomeDatabase;
		this.nomeSchema = nomeSchema;
		this.userName = userName;
		this.password = password;
	}

	public Connection gettConnection() throws SQLException {
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection("jdbc:" + tipoDatabase + "://localhost:" + indirizzoPorta + "/"
					+ nomeDatabase + "?currentSchema=" + nomeSchema + "&user=" + userName + "&password=" + password);
			System.out.println("connesione riuscita ");
		}
		return con;
	}
}
