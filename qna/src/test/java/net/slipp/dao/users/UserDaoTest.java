package net.slipp.dao.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.slipp.domain.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional

public class UserDaoTest {
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void findById() {
		User user = userDao.findById("javajigi");
		//log.debug("User : {}", user);
		log.debug(user.getUserId() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getEmail());
	}
	
	@Test
	public void create() throws Exception {
		User user = new User("sanjigi1", "password", "산지기", "sanjigi@gmail.com");
		userDao.create(user);
		User actual = userDao.findById(user.getUserId());
		
		assertThat(actual, is(user));
	}
}
