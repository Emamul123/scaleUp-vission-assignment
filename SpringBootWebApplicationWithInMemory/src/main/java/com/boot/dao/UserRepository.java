package com.boot.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.boot.bo.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject("select * from user where email=?", new Object[] { email },
				new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		jdbcTemplate.query("select * from user", (rs) -> {
			User user = new User();
			user.setUserId(rs.getInt("Id"));
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			users.add(user);
		});
		return users;
	}

	public int deleteByEmail(String email) {
		return jdbcTemplate.update("delete from user where email=?", new Object[] { email });
	}

	public int insert(User user) {
		return jdbcTemplate.update("insert into user (id, email, first_name,last_name) " + "values(?, ?, ?, ?)",
				new Object[] { user.getUserId(), user.getEmail(), user.getFirstName(), user.getLastName() });
	}

	public int update(User user) {
		return jdbcTemplate.update("update user " + " set first_name = ?, last_name = ? " + " where email = ?",
				new Object[] { user.getFirstName(), user.getLastName(), user.getEmail() });
	}

}
