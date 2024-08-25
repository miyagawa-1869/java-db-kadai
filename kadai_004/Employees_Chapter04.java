package kadai_004;

//インポート文
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {
	public static void main(String[] args) {
		Connection con = null;
		Statement statement = null;
//データベースへの接続実施・情報記入
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"W9yt94zc!");
			System.out.println("データベース接続成功:com.mysql.cj.jdbc.ConnectionImpl@xxxxxxxx");

			//		SQLクエリの準備
			statement = con.createStatement();
			String sql = """
					CREATE TABLE employees(
					id INT(11)NOT NULL AUTO_INCREMENT PRIMARY KEY,
					name VARCHAR(60)NOT NULL,
					email VARCHAR(255)NOT NULL,
					age INT(11),
					address VARCHAR(255)
					);
					""";
			//		クエリの実行（ＤＢＭＳに送信する）
			int rowCnt = statement.executeUpdate(sql);
			System.out.println("社員テーブルを作成しました:更新レコード=0" + rowCnt);
		} catch (SQLException e) {
			System.out.println("エラー発生:" + e.getMessage());
			//			使用したオブジェクトの解放
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}

}
