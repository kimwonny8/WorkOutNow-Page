package kjw59_project.controller.won.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjw59_project.controller.won.Action;
import kjw59_project.controller.won.ActionForward;

public class DefaultAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/team1/view/etc/error.jsp");
		
		return forward;
	}
}
