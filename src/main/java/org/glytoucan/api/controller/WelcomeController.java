package org.glytoucan.api.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glytoucan.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	private static final Log logger = LogFactory
			.getLog(WelcomeController.class);
	
	@Value("${application.message:OK}")
	private String message = "OK";

	@RequestMapping("/")
	public @ResponseBody Message welcome(Map<String, Object> model) {
		Message msg = new Message();
		msg.setError("");
		msg.setMessage(message);
		msg.setPath("/");
		msg.setStatus("200");
		msg.setTimestamp(new Date());
		return msg;
	}

	@RequestMapping("/status")
	public @ResponseBody Message status(Map<String, Object> model) {
		Message msg = new Message();
		msg.setError("");
		msg.setMessage(message + " status");
		msg.setPath("/status");
		msg.setStatus("200");
		msg.setTimestamp(new Date());
		return msg;
	}
}