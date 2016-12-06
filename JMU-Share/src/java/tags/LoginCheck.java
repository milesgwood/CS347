package tags;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
public class LoginCheck extends TagSupport {

  @Override
  public int doStartTag() throws JspTagException {
    HttpServletRequest request =
            (HttpServletRequest) this.pageContext.getRequest();
    HttpServletResponse response =
            (HttpServletResponse) this.pageContext.getResponse();
    HttpSession session = request.getSession(true);
   
    try {
      Boolean loggedIn = (Boolean) session.getAttribute("logged_in");
      if (loggedIn == null || !loggedIn) {
        response.sendRedirect(request.getContextPath());   // go to the home page
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return SKIP_BODY;
  }
}