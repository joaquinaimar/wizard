package com.wizard.web.application.system.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Test")
public class TestController {

	@RequestMapping(value = "/test.do", method = RequestMethod.POST)
	@ResponseBody
	public Boolean doLogin(@RequestParam String ID, @RequestParam String text1,
			@RequestParam String text2, @RequestParam String text3) {
		System.err.println("start");
		System.err.println(ID);
		System.err.println(text1);
		System.err.println(text2);
		System.err.println(text3);
		System.err.println("end");
		return true;
	}

}
