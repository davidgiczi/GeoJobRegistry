package hu.david.giczi.catvhungaria.georegister.model;

import java.io.File;
import java.io.FilenameFilter;

public class FolderManager {

	
	public static final String GEO_PATH = "\\\\10.0.1.74\\Tervezés\\GEO_Dávid\\";
	private String settlementFolderName;
	private String place_method_date;
	
	
	
	public FolderManager(String settlementFolderName, String place_method_date) {
		
		this.settlementFolderName = settlementFolderName;
		this.place_method_date = place_method_date;
	}
	
		
	public String getSettlementFolderName() {
		return settlementFolderName;
	}
	
	public void setSettlementFolderName(String settlementFolderName) {
		this.settlementFolderName = settlementFolderName;
	}
	
	public String getPlace_method_date() {
		return place_method_date;
	}


	public void setPlace_method_date(String place_method_date) {
		this.place_method_date = place_method_date;
	}


	public void createSettlementFolder() {
		
		File settlementFolder = new File(GEO_PATH + settlementFolderName);
		
		if(!settlementFolder.exists()) {
			
			settlementFolder.mkdir();
			
		}
		
	}

	public void createWorkFolder() {
		
		
		if(new File(GEO_PATH + settlementFolderName).exists()) {
			
			new File(GEO_PATH + settlementFolderName + "\\" + place_method_date).mkdir();
			
		}
		
	}
	
	public void createSubFolders() {
		
		if(new File(GEO_PATH + settlementFolderName + "\\" + place_method_date).exists()) {
			
			new File(GEO_PATH + settlementFolderName + "\\" + place_method_date + "\\mérés").mkdir();
			new File(GEO_PATH + settlementFolderName + "\\" + place_method_date + "\\állományok").mkdir();
			new File(GEO_PATH + settlementFolderName + "\\" + place_method_date + "\\munkarészek").mkdir();
		}
	}
	
	public String getMeasuringReportPath() {
		
		File measFolder = new File(GEO_PATH + settlementFolderName + "\\" + place_method_date + "\\mérés");
		
		if(measFolder.exists()) {
			
			String[] report = measFolder.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					
					return name.endsWith("txt");
				}
			});
			
			if(report.length == 1) {
				return measFolder.getAbsolutePath() + "\\" + report[0];
			}
			
		}
		
		
		
		return "-";
	}
	
	
}


