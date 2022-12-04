package kjw59_project.controller.won.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kjw59_project.controller.won.Action;
import kjw59_project.controller.won.ActionForward;

public class logoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("m_name",null);
		session.setAttribute("m_grade", null);
		session.setAttribute("m_id", null);
		session.setAttribute("loginState","logout");
		session.setAttribute("mi_thum_name", null);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/index.jsp");
		return forward;
	}

}
