package com.tud.lab4.service;

import java.util.List;

import com.tud.lab4.tp.*;

public interface RunnerManager {
	
	void addRunner(Runner runner);
	void deleteRunner(Runner runner);
	List<Runner> getAllRunners();
	void updateRunner(Runner runner, String newName);
	Runner runnerById(Long id);
	

}
