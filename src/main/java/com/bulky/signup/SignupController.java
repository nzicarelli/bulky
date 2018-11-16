package com.bulky.signup;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.account.AccountService;
import com.bulky.account.User;
import com.bulky.customer.Customer;
import com.bulky.customer.CustomerRepository;
import com.bulky.error.DataException;
import com.bulky.response.ResponseBuilder;
import com.bulky.response.ResponseData;
import com.bulky.support.AppUtil;
import com.bulky.support.email.EmailService;
import com.bulky.support.web.Ajax;

@Controller
class SignupController {

	private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ResponseBuilder responseBuilder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CustomerRepository customerRep;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("signup")
	String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(new SignupForm());
		if (Ajax.isAjaxRequest(requestedWith)) {
			return SIGNUP_VIEW_NAME.concat(" :: signupForm");
		}
		return SIGNUP_VIEW_NAME;
	}

	@PostMapping("api/users/signup")
	public @ResponseBody ResponseData signup(@ModelAttribute User user , HttpServletRequest request) {
		
		// test if user exists
		User u = accountService.findByEmail(user.getUemail());
		if (u !=null ) {
			return responseBuilder.userAlreadyExists(user.getUemail(), request.getLocale());
		}
		// create user blocked 
		user.setUrole(User.ROLES.ROLE_USER.name());
		user.setUenable(Boolean.FALSE);
		u = accountService.save(user, true);
		boolean success = u!=null && u.getUid()!=null;
		if (success) {
			try {
				// send email to user 
				emailService.sendActivationMail(user.getUid(), request.getLocale());
			} catch (Exception e) {
				return responseBuilder.fail(e);
			}
			return responseBuilder.success();
		}
		return responseBuilder.fail(new Exception("Signup error"));
	}
	
	
	@PostMapping("api/customer/signup")
	public @ResponseBody ResponseData signupCustomer(@RequestBody String payload, HttpServletRequest request) throws DataException {
		
		Customer user = AppUtil.bindObject(payload,Customer.class);
		// test if user exists
		Customer u = null;
		try {
			u = customerRep.findByUsername(user.getCuusername());
		} catch (Exception e) {}
		if (u !=null ) {
			return responseBuilder.userAlreadyExists(user.getCuusername(), request.getLocale());
		}
		// find user by cf / piva
		Customer umatch = null;
		try {
			umatch = customerRep.findByCfPiva(user.getCucf(), user.getCupiva());
		} catch (Exception e) {
		}
		if (umatch==null ) {
			user.setCunote("Cliente non trovato in anagrafica");
			user.setCudtins( new Date());
			user.setCudtmod( new Date() );
			user.setCuenabled(Boolean.FALSE);
			customerRep.store(user);
			return responseBuilder.customerNotFound(user.getCusurname(), request.getLocale());
		}
		// check is already registered
		if (!AppUtil.isEmpty(umatch.getCusurname()) ) {
			return responseBuilder.customerAlreadyExists(user.getCusurname(), request.getLocale());
		}
		umatch.setCupassword(passwordEncoder.encode(user.getCupassword()) );
		umatch.setCudtmod( new Date());
		umatch.setCusurname( user.getCusurname() );
		// FIXME DA AGGIUNGERE IL TELEFONO
		customerRep.store(umatch);
		
		boolean success = true;
		if (success) {
			try {
				// send email to user 
				emailService.sendCustomerActivationMail(user.getCuid(), request.getLocale());
			} catch (Exception e) {
				return responseBuilder.fail(e);
			}
			return responseBuilder.success();
		}
		return responseBuilder.fail(new Exception("Signup error"));
	}
}
