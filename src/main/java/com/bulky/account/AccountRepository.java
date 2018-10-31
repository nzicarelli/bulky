package com.bulky.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Users, Long> {

	Users findOneByEmail(String email);

	@Query("select count(a) > 0 from Users a where a.email = :email")
	boolean exists(@Param("email") String email);
}