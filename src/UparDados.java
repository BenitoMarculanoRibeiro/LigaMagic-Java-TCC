import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class UparDados {

	public static void main(String[] args) throws SQLException {

			Connection conexao = DriverManager
					.getConnection("http://localhost/phpmyadmin/db_structure.php?server=1&db=lm", "root", "");
			final String urlLojas = "ligamagicFrete.txt";
			Loja[] vetLoja = ControlArq.carregaLojas(urlLojas);
			EnviaDados en = new EnviaDados(vetLoja);
			en.run();
			conexao.close();
	}
}