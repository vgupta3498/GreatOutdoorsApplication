package com.cg.go.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.go.GreatOutdoorApplication;
import com.cg.go.dao.IGrowthReportRepository;
import com.cg.go.entity.GrowthReportEntity;
import com.cg.go.exception.GrowthReportException;
import java.util.Optional;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class GrowthReportServiceImplTest {
	
	@Mock
	IGrowthReportRepository repo;
	
	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	GrowthReportServiceImpl impl;
	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	GrowthReportEntity g;
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	@Autowired
	GrowthReportServiceImpl service;
	
	@Autowired
	IGrowthReportRepository growthRepo;
	
	@Test
	void contextLoads() {
		GreatOutdoorApplication.main(new String[] {});
	}
	
	
	//MOCK
	//tells JUnit that the public void method to which it is attached can be run as a test case
	@Test
	public void findAllGrowthReportTest() {
		GrowthReportEntity growthMock1 = new GrowthReportEntity(700, LocalDate.parse("2021-05-11"), 20000, 10000, 15, "Pink");
		when(repo.findAll()).thenReturn(Stream.of(growthMock1).collect(Collectors.toList()));
		assertEquals(1, impl.findAllGrowthReport().size());		
	}
	

	
	@Test
	public void deleteGrowthReportByIdTest() throws GrowthReportException{
		GrowthReportEntity entity = new GrowthReportEntity(700, LocalDate.parse("2021-05-11"), 20000, 10000, 15, "Pink");
		long id = entity.getGrowthReportId();
		Optional<GrowthReportEntity> og = Optional.ofNullable(entity);
		when(repo.findById(id)).thenReturn(og);
		assertEquals(0,impl.deleteGrowthReportById(id).size());
	}



	
	
	
	
	
	//JUNIT
	@Test
	public void findAllGrowthReportUnitTest() {
		List<GrowthReportEntity> list=service.findAllGrowthReport();
		assertNotNull(list,"List is present");
	}
		
	
	@Test //failed
	public void deleteAllGrowthReportUnitTest() throws GrowthReportException {
        List<GrowthReportEntity> list=service.deleteAllGrowthReport();
	    assertNotNull(list,"List is deleted");
	}
	
	
	@Test
	public void deleteAllGrowthReportFailUnitTest() {
		assertThrows(GrowthReportException.class, () -> service.deleteAllGrowthReport(),"Growth Report List not found exception occured");
	}
	
	
	@Test
	public void deleteGrowthReportByIdUnitTest() throws GrowthReportException {		
		GrowthReportEntity g=growthRepo.findById((long) 1).orElse(null);
		assertNotNull(g,"id is  present can delete");
		List<GrowthReportEntity> list=service.deleteGrowthReportById((long) 1);
		assertNotNull(list,"Growth Report updated");		
	}
	
	
	@Test
	public void deleteGrowthReportByIdFailUnitTest() {
		assertThrows(GrowthReportException.class, () -> service.deleteGrowthReportById((long) 2586),"Growth Report Id not found exception occured");
	}
}
