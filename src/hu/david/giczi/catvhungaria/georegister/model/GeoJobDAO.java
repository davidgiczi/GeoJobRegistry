package hu.david.giczi.catvhungaria.georegister.model;

import java.util.List;



public interface GeoJobDAO {

	
	void addGeoReg(GeoRegistration geoReg);

    void modify(GeoJob geoJob);

    void remove(Long id);

    List<GeoJob> findAll();
	
    List<GeoJob> search(String inputData);
    
    List<GeoJob> findByYear(String year);
    
    List<GeoJob> findByDate(String from, String to);
    
}
