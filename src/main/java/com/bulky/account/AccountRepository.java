package com.bulky.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

	User findOneByUemail(String email);
	
	User findOneByUname(String uname);
	
	@Query("select a from User a where a.uaccount = :uaccount order by a.uname")
	List<User> findAllByAccount(@Param("uaccount") Integer uaccount);

	@Query("select count(a) > 0 from User a where a.uemail = :email")
	boolean exists(@Param("email") String email);

	@Query("select a from User a where a.uid = :uid ")
	User findById(@Param("uid") Integer userId);
}