package com.shivam.spring.service;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.spring.dao.IdGeneratorDao;

/**
 * @author shivam
 *
 */


@Service
public class IdGeneratorService {

	@Autowired
	IdGeneratorDao idGeneratorDao;

	
	public int increaseNumberForID() throws Exception {
		
		int result = 0;
		try {
		result=	idGeneratorDao.doIncrementWithLock();
		System.out.println("data saved....");
		}catch (StaleObjectStateException e) {
			System.out.println("************** race condition occured, try again for perform this. *******************");
			/*try {
				Thread.sleep(1000);
				increaseNumberForID();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} */
			
		}catch (Exception e) {
			throw e;
		}
		
		return result;
		
	}
	
	
}
