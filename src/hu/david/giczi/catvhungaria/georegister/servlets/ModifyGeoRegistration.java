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
import hu.david.giczi.catvhungaria.georegister.model.InputDataValidator;


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
		GeoJobDAOImpl service =	new GeoJobDAOImpl();
		
		GeoRegistration modifiedGeoReg = new GeoRegistration(
				geoJobId,
				service.cutInputString(regParams.get("settlement")[0]),
				service.cutInputString(regParams.get("place")[0]),
				service.cutInputString(regParams.get("method")[0]),
				service.cutInputString(regParams.get("date")[0]),
				service.cutInputString(regParams.get("manager")[0]),
				service.cutInputString(regParams.get("investor")[0]),
				service.cutInputString(regParams.get("comment")[0]),
				isReady);
		
			
	if(InputDataValidator.isValidInputGeoRegistration(modifiedGeoReg)) {
		
		GeoJob modifiedGeoJob = new GeoJob(modifiedGeoReg);
		service.modify(modifiedGeoJob);
		service.createWorkFolders(modifiedGeoJob.getSettlementNameOfWork(), modifiedGeoJob.getPlaceOfWork() + "_"
				+ modifiedGeoJob.getMethod() + "_" + modifiedGeoJob.getDate());	
					
	}
	
	request.getRequestDispatcher("clearSession").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
