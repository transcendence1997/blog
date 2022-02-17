package com.example.blog;

import com.example.blog.mapper.UserMapper;
import com.example.blog.service.ArticleService;
import com.example.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;

	@Test
	void contextLoads() {
		System.out.println(userService.list(null));
	}

	@Test
	void encoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.matches("111222", "$2a$10$WhfZjxQY4pTPHaG.HvnCfePmRlpoZKsLJiEc80IIIbqgZo6Qy70LW"));
	}

	@Test
	void sqlTest() {
		System.out.println(userService.getPermissionsByUserId("0"));
	}

	@Test
	void textTest() {
		System.out.println(articleService.getArticleDetailById("1484646344088907777").getContent());
	}
}
