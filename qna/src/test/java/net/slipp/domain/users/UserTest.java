package net.slipp.domain.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	
	private static Validator validator;
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void userIdWhenIsEmpty() {
		User user = new User("", "password", "name", "javajigi@slipp.net");
		
		Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
		assertThat(constraintViolations.size(), CoreMatchers.is(2));
		
		//System.out.println(constraintViolations.size());
		for(ConstraintViolation<User> constraintViolation : constraintViolations) {
			log.debug("violation error message : {}", constraintViolation.getMessage());
		}
	}
	
	@Test
	public void matchPassword() throws Exception {
		String password = "password";
		Authenticate authenticate = new Authenticate("userId", password);
		User user = new User("userId", password, "name", "javajigi@slipp.net");
		assertTrue(user.matchPassword(authenticate));
		
		authenticate = new Authenticate("userId", "password2");
		assertFalse(user.matchPassword(authenticate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateWhenMisMatchUserId() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@slipp.net");
		User updateUser = new User("user", "password", "sanjigi", "sanjigi@slipp.net");
		user.update(updateUser);
	}
	
	@Test
	public void update() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@slipp.net");
		User updateUser = new User("userId", "password", "sanjigi", "sanjigi@slipp.net");
		User updatedUser = user.update(updateUser);
		assertThat(updatedUser, is(updateUser));
	}
}
