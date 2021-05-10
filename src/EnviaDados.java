import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnviaDados implements Runnable {
	private Loja[] loja;

	public EnviaDados(Loja[] loja) {
		this.loja = loja;
	}

	public void run() {
		for (int i = 0; i < loja.length; i++) {
			boolean t = true;
			do {
				try {
					Connection connect = DriverManager.getConnection(loja[i].getNome());
					String sql = "INSERT INTO `loja` (`id`, `loja`) VALUES (NULL, ?)";
					PreparedStatement stmt = connect.prepareStatement(sql);
					stmt.setString(1, loja[i].getNome());
					stmt.execute();
					stmt.close();
					t = false;
					System.out.println("i");
				} catch (SQLException e) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					System.out.println(e);
				}
			} while (t);
		}
	}
}
