package hu.david.giczi.catvhungaria.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showmail")
public class ShowEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowEmail() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String fileURL = request.getParameter("url");
		

		if (!"".equals(fileURL)) {

			Runtime run = Runtime.getRuntime();

			run.exec("C:\\Program Files (x86)\\Mozilla Thunderbird\\thunderbird " + fileURL);

		}

		request.getRequestDispatcher("clearSession").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
