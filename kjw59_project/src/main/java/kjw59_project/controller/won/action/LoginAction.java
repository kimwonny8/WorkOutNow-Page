package kjw59_project.controller.won.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import kjw59_project.controller.won.*;
import kjw59_project.controller.*;
import kjw59_project.model.won.*;

public class LoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("utf-8");
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO member = new MemberDTO();
		MImageDTO mImage = new MImageDTO();

		try {
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			String m_pw2 = encrypt(m_pw);

			member.setM_id(m_id);
			// 비밀번호 확인
			String Chk_m_pw = memberDAO.loginPwMember(member);

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);

			// 입력한 비밀번호랑 디비에 비밀번호랑 같다면 로그인
			if (m_pw.equals(Chk_m_pw) || m_pw2.equals(Chk_m_pw)) {
				memberDAO = new MemberDAO();
				String m_name = memberDAO.loginNameMember(member);

				memberDAO = new MemberDAO();
				String c_code = memberDAO.getC_codeMember(member);

				memberDAO = new MemberDAO();
				String m_grade = memberDAO.loginChkGrade(member);
				if (m_grade.equals("trainer"))
					m_grade = "트레이너";
				else if (m_grade.equals("trainerW"))
					m_grade = "트레이너(대기중)";
				else if (m_grade.equals("client"))
					m_grade = "일반";
				else if (m_grade.equals("admin"))
					m_grade = "관리자";

				// 썸네일 이름 가져와서 미리 저장해둠 -> 있으면 파일명, 없으면 null
				memberDAO = new MemberDAO();
				mImage.setM_id(m_id);
				String mi_thum_name = memberDAO.chkMemberPhoto(mImage);

				memberDAO = new MemberDAO();
				int m_coin = memberDAO.getCoinMember(member);

				session.setAttribute("m_id", m_id);
				session.setAttribute("m_name", m_name);
				session.setAttribute("c_code", c_code);
				session.setAttribute("m_grade", m_grade);
				session.setAttribute("m_coin", m_coin);
				session.setAttribute("loginState", "login");
				session.setAttribute("mi_thum_name", mi_thum_name);

				forward.setPath("/index.jsp");
				return forward;
			} else {
				return null;
			}
		} catch (Exception e) {
			response.sendRedirect("/kjw59_project/com/yju/2wda/team1/view/etc/error.jsp");
			return null;
		}
	}

	// 비밀번호 암호화
	public String encrypt(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes());

		return bytesToHex(md.digest());
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
}
