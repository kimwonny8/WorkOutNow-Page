package kjw59_project.model.won;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class productDAO {
	private PreparedStatement pstmt = null;
	private Connection con = null;

	Context init = null; // 컨텍스트 객체 변수
	DataSource ds = null; // 데이터소스 객체 변수

	ResultSet rs = null;

	public productDAO() {
		super();
		dbConnect();
	}

	public void dbConnect() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc_mariadb");
			con = ds.getConnection();

			System.out.println("DB연결 성공!!");
		} catch (Exception e) {
			System.out.println("DB연결 실패!!");
			e.printStackTrace();
		}
	}

	// 데이터베이스 연결 해제 메소드
	public void disConnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// pt 상품 게시글 업로드
	public boolean insertProduct(ptDTO pt) {
		boolean success = false;

		String sql = "insert into pt (t_id, pt_title, pt_one_c, pt_con_c, pt_content)";
		sql += "values (?, ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pt.getT_id());
			pstmt.setString(2, pt.getPt_title());
			pstmt.setInt(3, pt.getPt_one_c());
			pstmt.setInt(4, pt.getPt_con_c());
			pstmt.setString(5, pt.getPt_content());

			pstmt.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return success;
		} finally {
			disConnect();
		}

		return success;
	}

	// 게시판의 모든 레코드를 반환 메소드 - R
	public ArrayList<allClassVO> getClassList(ptDTO pt, memberDTO member, mImageDTO mImage) {
		ArrayList<allClassVO> classList = new ArrayList<allClassVO>();

		String sql = "select p.pt_code, p.t_id, p.pt_title, p.pt_one_c, p.pt_con_c, p.pt_content, p.pt_like, "
				+ "m.m_name, m.c_code, IFNULL(i.mi_thum_name, 'user.png') AS mi_thum_name "
				+ "from pt p left join m_image i on (p.t_id = i.m_id) left join member m on (p.t_id = m.m_id) order by p.pt_code";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next()) {
				allClassVO allClass = new allClassVO();
				allClass.setPt_code(rs.getInt("pt_code"));
				allClass.setT_id(rs.getString("t_id"));
				allClass.setPt_title(rs.getString("pt_title"));
				allClass.setPt_one_c(rs.getInt("pt_one_c"));
				allClass.setPt_con_c(rs.getInt("pt_con_c"));
				allClass.setPt_content(rs.getString("pt_content"));
				allClass.setPt_like(rs.getInt("pt_like"));
				allClass.setM_name(rs.getString("m_name"));
				allClass.setC_code(rs.getString("c_code"));
				allClass.setMi_thum_name(rs.getString("mi_thum_name"));

				classList.add(allClass);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

		return classList;
	}
}
