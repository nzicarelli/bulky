package com.bulky.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

	User findOneByUemail(String email);
	
	User findOneByUname(String uname);

	@Query("select count(a) > 0 from User a where a.uemail = :email")
	boolean exists(@Param("email") String email);
}