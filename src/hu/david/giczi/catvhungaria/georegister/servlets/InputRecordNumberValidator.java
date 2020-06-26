package hu.david.giczi.catvhungaria.georegister.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hu.david.giczi.catvhungaria.georegister.model.GeoJob;
import hu.david.giczi.catvhungaria.georegister.model.GeoJobDAOImpl;
import hu.david.giczi.catvhungaria.georegister.model.InputDataValidator;
import hu.david.giczi.catvhungaria.georegister.model.MeasuringReport;

@WebServlet("/validator")
public class InputRecordNumberValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InputRecordNumberValidator() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String inputData = request.getParameter("recordNumber");
		String operation = request.getParameter("operation");
		int recordNumber;
		GeoJob requestedGeoJob;

		if (InputDataValidator.isNumber(inputData)) {

			recordNumber = Integer.parseInt(inputData);
		} else {

			request.getSession().setAttribute("size", null);
			request.getSession().setAttribute("notNumber", true);
			request.getRequestDispatcher("postRouter").forward(request, response);
			return;
		}

		try {

			String requestInstruction = (String) request.getSession().getAttribute("requestInstruction");
			String searchedData = (String) request.getSession().getAttribute("searchedData");
			
			if( requestInstruction == null && searchedData == null ) {
				
				request.getRequestDispatcher("geostart.jsp").forward(request, response);
				return;
			}

			List<GeoJob> geoJobStore = new GeoJobDAOImpl().getGeoJobList(requestInstruction, searchedData);

			if (InputDataValidator.containRegs(geoJobStore.size(), recordNumber)) {

				requestedGeoJob = geoJobStore.get(recordNumber - 1);
				request.setAttribute("geojob", requestedGeoJob);
				createCoordReport(operation, requestedGeoJob, request);
				forwardForOperation(operation, request, response);
			} else {
				request.getSession().setAttribute("notNumber", null);
				request.getSession().setAttribute("size", geoJobStore.size());
				request.getRequestDispatcher("postRouter").forward(request, response);

			}

		} catch (IllegalStateException e) {

			request.getRequestDispatcher("geostart.jsp").forward(request, response);

		}

	}

	private void forwardForOperation(String operation, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		switch (operation) {
		
		case "modify":
			request.getRequestDispatcher("geojobmodify.jsp").forward(request, response);
			break;
		case "del":
			request.getRequestDispatcher("geojobdel.jsp").forward(request, response);
			break;
		case "createcoord":
			request.getRequestDispatcher("postRouter").forward(request, response);
			break;
		default:
			break;
		}

	}
	
	
	private void createCoordReport(String operation, GeoJob geoJob, HttpServletRequest request)  {
		
		request.getSession().setAttribute("notNumber", null);
		request.getSession().setAttribute("size", null);
		
		if("createcoord".equals(operation)) {
			
			if(MeasuringReport.createCoordReport( "\\\\10.0.1.74\\Tervezés\\GEO_Dávid\\",
													geoJob.getSettlementNameOfWork(),
					geoJob.getPlaceOfWork() + "_" + geoJob.getMethod() + "_"+geoJob.getDate())) {
				
				request.getSession().setAttribute("createcoord", true);
				
			}
			else {
				
				request.getSession().setAttribute("createcoord", false);
				
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
