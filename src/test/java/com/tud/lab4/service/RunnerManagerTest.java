package com.tud.lab4.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tud.lab4.tp.Runner;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class RunnerManagerTest {
	
	@Autowired
	RunnerManager runnerManager;
	
	private final String NAME_1 = "Mohamed";
	private final String LASTNAME_1 = "Farah";
	private final int YOB_1 = 1983;
	private final String NATIONALITY_1 = "GB";
	
	private final String NAME_2 = "Galen";
	private final String LASTNAME_2 = "Rupp";
	private final int YOB_2 = 1986;
	private final String NATIONALITY_2 = "USA";
	
	private final String NAME_3 = "Henryk";
	private final String LASTNAME_3 = "Szost";
	private final int YOB_3 = 1983;
	private final String NATIONALITY_3 = "Poland";
	
	private final String NAME_4 = "Kenenisa";
	private final String LASTNAME_4 = "Bekele";
	private final int YOB_4 = 1982;
	private final String NATIONALITY_4 = "Kenya";
	
	@Test
	public void checkAdding(){
		Runner runner = new Runner();
		
		runner.setFirstName(NAME_1);
		runner.setLastName(LASTNAME_1);
		runner.setYob(YOB_1);
		runner.setNationality(NATIONALITY_1);
		
		runnerManager.addRunner(runner);
		
		List<Runner> runners = runnerManager.getAllRunners();
		
		Runner retrivedRunner = new Runner();
		
		retrivedRunner = runners.get(runners.size()-1);
		
		assertEquals(NAME_1, retrivedRunner.getFirstName());
		assertEquals(LASTNAME_1, retrivedRunner.getLastName());
		assertEquals(YOB_1,retrivedRunner.getYob());
		assertEquals(NATIONALITY_1, retrivedRunner.getNationality());
		
		
	}
	
	@Test
	public void checkDelete(){
		Runner runner = new Runner();
		
		runner.setFirstName(NAME_2);
		runner.setLastName(LASTNAME_2);
		runner.setYob(YOB_2);
		runner.setNationality(NATIONALITY_2);
		List<Runner> runners = runnerManager.getAllRunners();
		int count = runners.size();
		runnerManager.addRunner(runner);
		runners.clear();
		runners = runnerManager.getAllRunners();
		
		Long id = runners.get(runners.size()-1).getId();
		runnerManager.deleteRunner(runner);
		runners = runnerManager.getAllRunners();
		assertEquals(count, runners.size());
		Runner newRunner = new Runner();
		
		newRunner = runners.get(runners.size()-1);

		for(Runner run : runners){
			assertTrue(run.getId() != id);
		}	
	}
	@Test
	public void checkUpdate(){
		
		Runner runner = new Runner();
		
		runner.setFirstName(NAME_3);
		runner.setLastName(LASTNAME_3);
		runner.setYob(YOB_3);
		runner.setNationality(NATIONALITY_3);
		runnerManager.addRunner(runner);
		
		List<Runner> runners = runnerManager.getAllRunners();
		
		String name = "Zenon";
		runnerManager.updateRunner(runner, name);
		
		runners = runnerManager.getAllRunners();
		assertEquals(runners.get(runners.size()-1).getFirstName(), name);
		
		runnerManager.deleteRunner(runner);
		runners = runnerManager.getAllRunners();
		for(Runner run : runners){
			assertNotSame(name,run.getFirstName());
		}	
	}
	
	@Test
	public void checkIdSelect(){
		Runner runner = new Runner();
		
		runner.setFirstName(NAME_4);
		runner.setLastName(LASTNAME_4);
		runner.setYob(YOB_4);
		runner.setNationality(NATIONALITY_4);
		runnerManager.addRunner(runner);
		
		List<Runner> runners = runnerManager.getAllRunners();
		Long idToCheck = runners.get(runners.size()-1).getId();
		Runner runnerById = runnerManager.runnerById(idToCheck);
		assertEquals(NAME_4, runnerById.getFirstName());
		assertEquals(LASTNAME_4, runnerById.getLastName());
		assertEquals(YOB_4, runnerById.getYob());
		assertEquals(NATIONALITY_4,runnerById.getNationality());
	}
	@Test
	public void checkLastNameSelect(){
		List<Runner> runners = runnerManager.getAllRunners();
		String lastName = runners.get(0).getLastName();
		runners = runnerManager.getAllRunners();
		assertEquals(lastName, runners.get(0).getLastName());
	}
}

