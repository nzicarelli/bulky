/**
 * 
 */
package com.bulky.controller.custom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.account.User.ROLES;
import com.bulky.action.ActionRepository;
import com.bulky.action.CatgAction;
import com.bulky.custom.catg.CatgRepository;
import com.bulky.custom.catg.CatgRifiuti;
import com.bulky.customer.CustomerRepository;
import com.bulky.error.DataException;
import com.bulky.response.ResponseBuilder;
import com.bulky.response.ResponseData;
import com.bulky.security.TokenHelper;

/**
 * @author kaala
 *
 */
@Controller
public class CustomController {
	
	@Autowired
	private CatgRepository catgRep;
	
	@Autowired
	private ResponseBuilder builder;
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@PostMapping("api/custom/list-catg")
	public @ResponseBody ResponseData listCatg(@RequestBody String payload, HttpServletRequest request) throws DataException {
		
		String userKind = tokenHelper.getUserKind(request);
		Integer idAccount = tokenHelper.getIdAccount(request);
		ROLES r = null;
		if (userKind!=null) {
			r = ROLES.valueOf(userKind);
		}
		List<CatgRifiuti> catga = catgRep.listAll(idAccount, r);
		return builder.success(catga);
	}

}
