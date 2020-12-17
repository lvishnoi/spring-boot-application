package com.programming.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.programming.App;
import com.programming.model.StatusUpdate;
import com.programming.model.StatusUpdateDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Transactional
public class StatusTest {

	@Autowired
	private StatusUpdateDao statusUpdateDao;

	@Test
	public void testSave() {
		StatusUpdate status = new StatusUpdate("This is a test status update.");

		statusUpdateDao.save(status);

		assertNotNull("Non-null ID", status.getId());
		assertNotNull("Non-null Date", status.getAdded());

		Optional<StatusUpdate> retrieved = statusUpdateDao.findById(status.getId());
		assertEquals("Matching status update", Optional.of(status), retrieved);
	}
	
	@Test
	public void testFindLatest() {
		Calendar calender = Calendar.getInstance();
		StatusUpdate lastStatusUpdate = null;
		
		for (int i = 0; i<10; i++) {
			calender.add(Calendar.DAY_OF_YEAR, 1);
			StatusUpdate statusUpdate = new StatusUpdate("Status update" + i, calender.getTime());
			statusUpdateDao.save(statusUpdate);
			lastStatusUpdate = statusUpdate;
		}
		
		StatusUpdate retrieved = statusUpdateDao.findFirstByOrderByAddedDesc();
		assertEquals("Latest status update", lastStatusUpdate, retrieved);
	}
}
