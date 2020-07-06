package hu.david.giczi.catvhungaria.georegister.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;




public class GeoJobDAOImpl implements GeoJobDAO {

	
	@Override
	public void addGeoReg(GeoRegistration geoReg) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanningRegister");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(geoReg);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

	@Override
	public List<GeoJob> findAll() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanningRegister");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<GeoRegistration> query = em.createQuery("SELECT g FROM GeoRegistration g", GeoRegistration.class);
		
		List<GeoRegistration> geoRegs = query.getResultList();
		
		em.close();
		emf.close();
		
		return createGeoJob(geoRegs);
	}

	
	private List<GeoJob> createGeoJob(List<GeoRegistration> geoRegs){
		
		List<GeoJob> geoJobStore = new ArrayList<>();
		
		geoRegs.forEach(reg -> geoJobStore.add(new GeoJob(reg)));
		
		Collections.sort(geoJobStore);
		
		return geoJobStore;
	}

	@Override
	public void modify(GeoJob geoJob) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanningRegister");
		EntityManager em = emf.createEntityManager();
		
		GeoRegistration geoReg = em.find(GeoRegistration.class, geoJob.getId());
		
		em.getTransaction().begin();
		
		geoReg.setSettlementNameOfWork(geoJob.getSettlementNameOfWork());
		geoReg.setPlaceOfWork(geoJob.getPlaceOfWork());
		geoReg.setMethod(geoJob.getMethod());
		geoReg.setDate(geoJob.getDate());
		geoReg.setInvestmentManager(geoJob.getInvestmentManager());
		geoReg.setInvestorCompany(geoJob.getInvestorCompany());
		geoReg.setComment(geoJob.getComment());
		geoReg.setIsReady(geoJob.getIsReady());
		
		em.getTransaction().commit();
		em.close();
		emf.close();
			
	}

	@Override
	public void remove(Long id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanningRegister");
		EntityManager em = emf.createEntityManager();
		
		GeoRegistration geoReg = em.find(GeoRegistration.class, id);
		
		em.getTransaction().begin();
		em.remove(geoReg);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

	@Override
	public List<GeoJob> search(String inputData) {
		
		List<GeoJob> allGeoJobs = findAll();
		
		List<GeoJob> result = allGeoJobs.stream()
										.filter(job -> !inputData.isEmpty()
												 && (job.getSettlementNameOfWork().contains(inputData)
												 || job.getPlaceOfWork().contains(inputData)
												 || job.getMethod().contains(inputData)
												 || job.getDate().contains(inputData)
												 || job.getInvestmentManager().contains(inputData)
												 || job.getInvestorCompany().contains(inputData)
												 || job.getComment().contains(inputData)))
										.collect(Collectors.toList());	
		return result;
	}

	@Override
	public List<GeoJob> findByYear(String year) {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanningRegister");
	EntityManager em = emf.createEntityManager();
	
	TypedQuery<GeoRegistration> query =
			em.createQuery("SELECT g FROM GeoRegistration g WHERE g.date LIKE \'" + year + "%\'", GeoRegistration.class);
		
	List<GeoRegistration> results = query.getResultList();
	
	em.close();
	emf.close();
		
	return createGeoJob(results);
	
	}
	
	public String cutInputString(String input) {

		if (input.length() > 255) {

			input = input.substring(0, 255);

		}

		return input;
	}

	public void createWorkFolders(String settlement, String place_method_date) {

		FolderManager manager = new FolderManager(settlement, place_method_date);
		manager.createSettlementFolder();
		manager.createWorkFolder();
		manager.createSubFolders();
	}
	
	public List<GeoJob> getGeoJobList(String request, String searchedData){
		
		List<GeoJob> geoJobList = new ArrayList<>();
		
		
		if("all".equals(request)) {
			
			geoJobList = findAll();
		}
		else if(!"all".equals(request) && searchedData == null) {
			
			geoJobList = findByYear(request);
		}
		else if(searchedData != null) {
			
			geoJobList = search(searchedData);
		}
		
		
		return geoJobList;
	}

	@Override
	public List<GeoJob> findByDate(String from, String to) {
		
		Long startDate = GeoJob.parseGeoJobDate(from);
		Long stopDate = GeoJob.parseGeoJobDate(to);
		
		List<GeoJob> store = findAll();
		List<GeoJob> result;
		
		if(startDate <= stopDate) {
			 result = store.stream()
					.filter(job -> startDate <= GeoJob.parseGeoJobDate(job.getDate())
					&& GeoJob.parseGeoJobDate(job.getDate()) <= stopDate)
					.collect(Collectors.toList());	
		}
		else {
			result = store.stream()
					.filter(job -> stopDate < GeoJob.parseGeoJobDate(job.getDate())
					&& GeoJob.parseGeoJobDate(job.getDate()) < startDate)
					.collect(Collectors.toList());	
		}
		
		return result;
	}
}
