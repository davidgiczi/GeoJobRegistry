package hu.david.giczi.catvhungaria.georegister.servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hu.david.giczi.catvhungaria.georegister.model.GeoJob;
import hu.david.giczi.catvhungaria.georegister.model.GeoJobDAOImpl;
import hu.david.giczi.catvhungaria.georegister.model.GeoRegistration;


@WebServlet("/modifyGeoJob")
public class ModifyGeoRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ModifyGeoRegistration() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map<String, String[]> regParams = request.getParameterMap();
		
		Boolean isReady = regParams.get("ready") == null ? false : true;
		Long geoJobId = Long.parseLong(regParams.get("geojobid")[0]);
		
		GeoJob modifiedJob = new GeoJob(
						new GeoRegistration(
						geoJobId,
						regParams.get("settlement")[0],
						regParams.get("place")[0],
						regParams.get("method")[0],
						regParams.get("date")[0],
						regParams.get("manager")[0],
						regParams.get("investor")[0],
						regParams.get("comment")[0],
						isReady));
		
	GeoJobDAOImpl service =	new GeoJobDAOImpl();
	service.modify(modifiedJob);
	service.createWorkFolders(modifiedJob.getSettlementNameOfWork(), modifiedJob.getPlaceOfWork() + "_"
			+ modifiedJob.getMethod() + "_" + modifiedJob.getDate());	
		
	request.getRequestDispatcher("clearSession").forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
