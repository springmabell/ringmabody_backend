package com.example.jpetstore.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import com.example.jpetstore.domain.Class;

import com.example.jpetstore.dao.ClassDao;

@Service
@Transactional
public class SchedulerImpl implements SchedulerFacade {

	@Autowired
	private ClassDao classDao;

	@Autowired
	private ThreadPoolTaskScheduler scheduler;

	@Override
	public void test(Date edate) {
		// TODO Auto-generated method stub
		Runnable updateTableRunner = new Runnable() {
			@Override
			public void run() {
				
				  Date today = new Date(); 
				  classDao.closeEvent(today);
				  classDao.deleteFinishedClassFromCart();

			}
		};
		scheduler.schedule(updateTableRunner, edate);
	}

}
