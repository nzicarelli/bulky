/**
 * 
 */
package com.bulky.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.account.AccountService;
import com.bulky.account.User;
import com.bulky.account.User.ROLES;
import com.bulky.response.ResponseData;
import com.bulky.security.TokenHelper;
import com.bulky.support.AppUtil;

/**
 * @author kaala
 *
 */

@Controller
public class UserController {
	
	@Autowired
	private AccountService accountSrv;
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@PostMapping("api/user/list")
	public @ResponseBody ResponseData listUsers(@RequestBody String payload, HttpServletRequest request) {
		try {
			Integer idAccount = tokenHelper.getIdAccount(request);
			String userKind = tokenHelper.getUserKind(request);
			if (ROLES.ROLE_CUSTOMER.equals(userKind)) {
				throw new AccessDeniedException(userKind+" FORBIDDEN !");
			}
			List<User> users = accountSrv.findAll(idAccount);
			if (users!=null) {
				for(User u: users) {
					u.setUpasswd(null);
				}
			}
			return new ResponseData(true, null, users, null, null);
		} catch (Exception e) {
			return new ResponseData(false, e.getMessage(), new Object(), e ,null);
		}
		
	}
	
	
	@PostMapping("api/user/add-update")
	public @ResponseBody ResponseData addOrUpdateUser(@RequestBody String payload, HttpServletRequest request) {
		try {
			Integer idAccount = tokenHelper.getIdAccount(request);
			String userKind = tokenHelper.getUserKind(request);
			if (!ROLES.ROLE_ADMIN.name().equals(userKind) ) {
				throw new AccessDeniedException(userKind+" FORBIDDEN !");
			}
			User u = AppUtil.bindObject(payload, User.class);
			boolean updatePwd = false;
			if (u.getUid()!=null && u.getUid().intValue()>0) {
				User x = accountSrv.findById(u.getUid());
				u.setUpasswd( x.getUpasswd() );
			}else{
				updatePwd = true;
				u.setUaccount(idAccount);			
			}
			if (u.getUrole()==null || u.getUrole().trim().length()==0) {
				u.setUrole(ROLES.ROLE_USER.name());
			}
			u = accountSrv.save(u, updatePwd );
			u.setUpasswd(null);
			return new ResponseData(true, null, u, null, null);
		} catch (Exception e) {
			return new ResponseData(false, e.getMessage(), new Object(), e ,null);
		}
		
	}
	
	@PostMapping("api/user/disable")
	public @ResponseBody ResponseData disableUser(@RequestBody String payload, HttpServletRequest request) {
		try {
			// Integer idAccount = tokenHelper.getIdAccount(request);
			String userKind = tokenHelper.getUserKind(request);
			if (!ROLES.ROLE_ADMIN.name().equals(userKind) ) {
				throw new AccessDeniedException(userKind+" FORBIDDEN !");
			}
			JSONObject plo = AppUtil.toPayLoad(payload);
			Integer uid = AppUtil.getIntegerValueOf(plo, "uid");
			
			User u = accountSrv.findById(uid);
			if (u!=null) {
				u.setUenable(Boolean.FALSE);
			}
			boolean updatePwd = false;		
			u = accountSrv.save(u, updatePwd );
			u.setUpasswd(null);
			return new ResponseData(true, null, u, null, null);
		} catch (Exception e) {
			return new ResponseData(false, e.getMessage(), new Object(), e ,null);
		}
		
	}
	
	@PostMapping("api/user/enable")
	public @ResponseBody ResponseData enableUser(@RequestBody String payload, HttpServletRequest request) {
		try {
			// Integer idAccount = tokenHelper.getIdAccount(request);
			String userKind = tokenHelper.getUserKind(request);
			if (!ROLES.ROLE_ADMIN.name().equals(userKind) ) {
				throw new AccessDeniedException(userKind+" FORBIDDEN !");
			}
			JSONObject plo = AppUtil.toPayLoad(payload);
			Integer uid = AppUtil.getIntegerValueOf(plo, "uid");
			
			User u = accountSrv.findById(uid);
			if (u!=null) {
				u.setUenable(Boolean.TRUE);
			}
			boolean updatePwd = false;		
			u = accountSrv.save(u, updatePwd );
			u.setUpasswd(null);
			return new ResponseData(true, null, u, null, null);
		} catch (Exception e) {
			return new ResponseData(false, e.getMessage(), new Object(), e ,null);
		}
		
	}
	
	@PostMapping("api/user/update-pwd")
	public @ResponseBody ResponseData updatePasswd(@RequestBody String payload, HttpServletRequest request) {
		try {
			// Integer idAccount = tokenHelper.getIdAccount(request);
			String userKind = tokenHelper.getUserKind(request);
			if (!ROLES.ROLE_ADMIN.name().equals(userKind) ) {
				throw new AccessDeniedException(userKind+" FORBIDDEN !");
			}
			JSONObject plo = AppUtil.toPayLoad(payload);
			Integer uid = AppUtil.getIntegerValueOf(plo, "uid");
			String newPwd = AppUtil.getStringValueOf(plo, "pwd");			
			User u = accountSrv.findById(uid);
			u.setUpasswd(newPwd);
			boolean updatePwd = true;		
			u = accountSrv.save(u, updatePwd );
			u.setUpasswd(null);
			return new ResponseData(true, null, u, null, null);
		} catch (Exception e) {
			return new ResponseData(false, e.getMessage(), new Object(), e ,null);
		}
		
	}

}
