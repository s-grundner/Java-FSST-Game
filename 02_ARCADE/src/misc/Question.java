package misc;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public class Question {

	private int id;
	private int punkte;
	private String q;
	private String a;

	public Question(int id, int punkte, String q, String a) {
		this.id = id;
		this.punkte = punkte;
		this.q = q;
		this.a = a.toLowerCase();
	}

	public boolean isCorrect(String a) {
		if (a != null) {
			if (this.a.equals(a.toLowerCase())) { return true; }
		}
		return false;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public int getPunkte() { return punkte; }

	public void setPunkte(int punkte) { this.punkte = punkte; }

	public String getQ() { return q; }

	public void setQ(String q) { this.q = q; }

	public String getA() { return a; }

	public void setA(String a) { this.a = a; }

	@Override
	public String toString() {
		String ret = "";
		ret += q + " \n";
		ret += a + " \n";
		ret += punkte + " \n";

		return ret;
	}
}
