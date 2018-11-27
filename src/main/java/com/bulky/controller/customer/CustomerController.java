/**
 * 
 */
package com.bulky.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.customer.Address;
import com.bulky.customer.Customer;
import com.bulky.customer.CustomerRepository;
import com.bulky.error.DataException;
import com.bulky.response.ResponseBuilder;
import com.bulky.response.ResponseData;
import com.bulky.security.TokenHelper;
import com.bulky.support.AppUtil;

/**
 * @author kaala
 *
 */
@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRep;
	
	@Autowired
	private ResponseBuilder builder;
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@PostMapping("api/customer/address")
	public @ResponseBody ResponseData listAction(@RequestBody String payload, HttpServletRequest request) throws DataException {
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer id = AppUtil.getIntegerValueOf(plObj,"cuid");
		if (AppUtil.isEmpty(id)) {
			return builder.insufficienParameters("cuid", request.getLocale());
		}
		List<Address> addresses = customerRep.listAddressByCustomer(id);
		return builder.success(addresses);
	}
	
	@PostMapping("api/customer/list-by-comune")
	public @ResponseBody ResponseData listCustomerByComune(@RequestBody String payload, HttpServletRequest request) throws DataException {
		
		Integer account = tokenHelper.getIdAccount(request);
		JSONObject plObj = AppUtil.toPayLoad(payload);
		String comune = AppUtil.getStringValueOf(plObj,"comune");
		if (AppUtil.isEmpty(comune)) {
			return builder.insufficienParameters("comune", request.getLocale());
		}
		Integer _limit = AppUtil.getIntegerValueOf(plObj, "limit");
		Integer _start = AppUtil.getIntegerValueOf(plObj, "start");
		int limit = _limit == null ?1000:_limit.intValue();
		int start = _start == null ?0:_start.intValue();
		List<Customer> customers = customerRep.listCustomerByComune(account,comune,start,limit);
		return builder.success(customers);
	}

}
