package com.programming.test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

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
}
