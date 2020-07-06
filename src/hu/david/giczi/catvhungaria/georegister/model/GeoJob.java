package hu.david.giczi.catvhungaria.georegister.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GeoJob implements Serializable, Comparable<GeoJob> {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String settlementNameOfWork;
	private String placeOfWork;
	private String method;
	private String date;
	private String investmentManager;
	private String investorCompany;
	private String comment;
	private String measTime;
	private String measPointNumber;
	private String measDist;
	private Boolean isReady;
	
	public GeoJob() {
		
	}
	
	public GeoJob(GeoRegistration geoReg) {
		
		this.id = geoReg.getId();
		this.settlementNameOfWork = geoReg.getSettlementNameOfWork();
		this.placeOfWork = geoReg.getPlaceOfWork();
		this.method = geoReg.getMethod();
		this.date = geoReg.getDate();
		this.investmentManager = geoReg.getInvestmentManager();
		this.investorCompany = geoReg.getInvestorCompany();
		this.comment = geoReg.getComment();
		this.isReady = geoReg.getIsReady();
		
		addMeasuredDataToGeoJob();
	}


	private void addMeasuredDataToGeoJob() {
			
		MeasuringReport report = new MeasuringReport(
				new FolderManager(settlementNameOfWork, placeOfWork + "_" + method + "_" + date).getMeasuringReportPath());
		
		this.measTime = report.getMeasData().isEmpty() ? "-" :
			report.getStartTimeOfMeasuring() + " - " + report.getStopTimeOfMeasuring() + " [" + report.getDurationOfMeasuring() + "]";
		this.measPointNumber = report.getMeasData().isEmpty() ? "-" : String.valueOf(report.getNumberOfMeasuredPoint());
		this.measDist = report.getMeasData().isEmpty() ? "-" : report.getTheLongestMeasuredDistance();
		
	}
	
		
	public Long getId() {
		return id;
	}


	public String getSettlementNameOfWork() {
		return settlementNameOfWork;
	}


	public String getPlaceOfWork() {
		return placeOfWork;
	}


	public String getMethod() {
		return method;
	}


	public String getDate() {
		return date;
	}


	public String getInvestmentManager() {
		return investmentManager;
	}


	public String getInvestorCompany() {
		return investorCompany;
	}


	public String getComment() {
		return comment;
	}


	public String getMeasTime() {
		return measTime;
	}


	public String getMeasPointNumber() {
		return measPointNumber;
	}


	public String getMeasDist() {
		return measDist;
	}


	public Boolean getIsReady() {
		return isReady;
	}

	public void setSettlementNameOfWork(String settlementNameOfWork) {
		this.settlementNameOfWork = settlementNameOfWork;
	}


	public void setPlaceOfWork(String placeOfWork) {
		this.placeOfWork = placeOfWork;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setInvestmentManager(String investmentManager) {
		this.investmentManager = investmentManager;
	}


	public void setInvestorCompany(String investorCompany) {
		this.investorCompany = investorCompany;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public void setMeasTime(String measTime) {
		this.measTime = measTime;
	}


	public void setMeasPointNumber(String measPointNumber) {
		this.measPointNumber = measPointNumber;
	}


	public void setMeasDist(String measDist) {
		this.measDist = measDist;
	}


	public void setIsReady(Boolean isReady) {
		this.isReady = isReady;
	}


	@Override
	public int compareTo(GeoJob o) {
			
		
	return parseGeoJobDate(this.date) <=  parseGeoJobDate(o.getDate()) ? -1 : 1;
			
	}
	
	public static long parseGeoJobDate(String date)  {
		
		String[] data = date.split("\\s+");
		
		try {
			if(data != null && data.length != 0) {
				return new SimpleDateFormat("yyyy-MM-dd").parse(data[0]).getTime();
			}
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return 0L;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((investmentManager == null) ? 0 : investmentManager.hashCode());
		result = prime * result + ((investorCompany == null) ? 0 : investorCompany.hashCode());
		result = prime * result + ((isReady == null) ? 0 : isReady.hashCode());
		result = prime * result + ((measDist == null) ? 0 : measDist.hashCode());
		result = prime * result + ((measPointNumber == null) ? 0 : measPointNumber.hashCode());
		result = prime * result + ((measTime == null) ? 0 : measTime.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((placeOfWork == null) ? 0 : placeOfWork.hashCode());
		result = prime * result + ((settlementNameOfWork == null) ? 0 : settlementNameOfWork.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoJob other = (GeoJob) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (investmentManager == null) {
			if (other.investmentManager != null)
				return false;
		} else if (!investmentManager.equals(other.investmentManager))
			return false;
		if (investorCompany == null) {
			if (other.investorCompany != null)
				return false;
		} else if (!investorCompany.equals(other.investorCompany))
			return false;
		if (isReady == null) {
			if (other.isReady != null)
				return false;
		} else if (!isReady.equals(other.isReady))
			return false;
		if (measDist == null) {
			if (other.measDist != null)
				return false;
		} else if (!measDist.equals(other.measDist))
			return false;
		if (measPointNumber == null) {
			if (other.measPointNumber != null)
				return false;
		} else if (!measPointNumber.equals(other.measPointNumber))
			return false;
		if (measTime == null) {
			if (other.measTime != null)
				return false;
		} else if (!measTime.equals(other.measTime))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (placeOfWork == null) {
			if (other.placeOfWork != null)
				return false;
		} else if (!placeOfWork.equals(other.placeOfWork))
			return false;
		if (settlementNameOfWork == null) {
			if (other.settlementNameOfWork != null)
				return false;
		} else if (!settlementNameOfWork.equals(other.settlementNameOfWork))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "GeoJob [id=" + id + ", settlementNameOfWork=" + settlementNameOfWork + ", placeOfWork=" + placeOfWork
				+ ", method=" + method + ", date=" + date + ", investmentManager=" + investmentManager
				+ ", investorCompany=" + investorCompany + ", comment=" + comment + ", measTime=" + measTime
				+ ", measPointNumber=" + measPointNumber + ", measDist=" + measDist + ", isReady=" + isReady + "]";
	}


	
	
	
	
}
