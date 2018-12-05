package com.haiyu;

import com.haiyu.manager.ManagerApplication;
import com.haiyu.manager.dao.BaseAdminUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ManagerApplication.class})
public class ManagerApplicationTests {

	@Autowired
	private BaseAdminUserMapper baseAdminUserMapper;

	@Test
	public void contextLoads() {
		String  password = "1,9";
		String[] split = password.split(",");
		for (String s:split){
			System.out.println(s);
		}

	}

}
