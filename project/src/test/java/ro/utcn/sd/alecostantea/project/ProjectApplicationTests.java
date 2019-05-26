package ro.utcn.sd.alecostantea.project;

import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.utcn.sd.alecostantea.project.model.User;
import ro.utcn.sd.alecostantea.project.persistence.JPA.UserRepository;

import javax.persistence.EntityManager;

public class ProjectApplicationTests {
//	private final UserRepository userRepository = new UserRepository(EntityManager et);
//	private User user1;
//	private User user2;
//
//	@Before
//	public void Load(){
//		user1 = new User(null,"ale","sarmale");
//		userRepository.save(user1);
//	}
//
//	@Test
//	public void TestAddUser(){
//		int beforeLen = userRepository.findALL().size();
//
//		user2 = new User(null,"sunca","feliata");
//		userRepository.save(user2);
//
//		int afterLen = userRepository.findALL().size();
//
//		Assert.assertEquals(beforeLen+1,afterLen);
//	}
//
//	@After
//	public void Empty(){
//		userRepository.remove(user1);
//		userRepository.remove(user2);
//	}

}
