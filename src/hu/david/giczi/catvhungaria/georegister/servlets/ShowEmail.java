package hu.david.giczi.catvhungaria.georegister.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.catvhungaria.georegister.model.GeoJobPropertyStore;

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
		
		@SuppressWarnings (value="unchecked")
		List<String> urlStore = (List<String>) request.getSession().getAttribute("fileURLStore");
		
		if( !urlStore.isEmpty() && urlStore.get(0).endsWith(".eml")) {
		
		String fileURL = urlStore.get(0);

		Runtime run = Runtime.getRuntime();

		run.exec(GeoJobPropertyStore.URL3 + fileURL);

		}
		
		request.getRequestDispatcher("clearSession").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
