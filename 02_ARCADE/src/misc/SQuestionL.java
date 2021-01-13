package misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public class SQuestionL {

	private ArrayList<Question> qList;

	public SQuestionL() {
		this.qList = new ArrayList<>();
		init();
	}

	private void init() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:src/assets/fragen-spiel-bauer.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM FRAGEN;");

			while (rs.next()) {
				int id = rs.getInt("id");
				String fragentext = rs.getString("fragentext");
				String loesungstext = rs.getString("loesungstext");
				int punkte = rs.getInt("punkte");

				qList.add(new Question(id, punkte, fragentext, loesungstext));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public ArrayList<Question> getQList() { return qList; }

	public Question getRandomQ() {
		Random rnd = new Random();
		return qList.get(rnd.nextInt(qList.size()));
	}
}
