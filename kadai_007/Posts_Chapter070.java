package kadai_007;

//データベース接続の為インポート文
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter070 {

	public static void main(String[] args) {
		//変数の宣言  メソッド		
		Connection con = null;
		Statement statement = null;

		//データベース接続用のgetconnectionメソッド		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"W9yt94zc!");
			System.out.println("データベース接続成功" + con);

			//SQLクエリを実行するステートメント
			statement = con.createStatement();

			System.out.println("レコードの追加を実行します");

			//追加するデータ用SQLを準備
			String sql = "INSERT INTO posts(user_id,posted_at,post_content,likes) VALUES"
					+ "(1003,'2023-02-08','昨日の夜は徹夜でした・・',13),"
					+ "(1002,'2023-02-08','お疲れ様です！',12),"
					+ "(1003,'2023-02-09','今日も頑張ります！',18),"
					+ "(1001,'2023-02-09','油断は禁物ですよ！',17),"
					+ "(1002,'2023-02-10','明日から連休ですね！',20)";

			//SQLクエリの実行（DBMSに送信する）	       
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが追加されました");

			//データの検索 SQLクエリを準備
			sql = "SELECT * FROM posts WHERE user_id =1002;";

			//SQLクエリを実行（DBMSに送信する）
			ResultSet result = statement.executeQuery(sql);
			System.out.println("ユーザーIDが１００２のレコードを検索致しました");

			//SQLクエリの実行結果を抽出	        
			while (result.next()) {
				Date postedAt = result.getDate("posted_at");
				String postcontent = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(result.getRow() + "件目：投稿日時＝" + postedAt + "/投稿内容＝" + postcontent + "いいね数＝" + likes);
			}

			//使用したオブジェクトの解放
		} catch (SQLException e) {
			System.out.println("データベース接続失敗:" + e.getMessage());

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
