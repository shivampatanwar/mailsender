package com.jsp.mailsender.controller;


import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.internet.MimeMessage;

@Controller
public class MyController {
	
	@Autowired
	JavaMailSender mailSender;
	
	@GetMapping("/")
	public String loadHome() {
		return "index";
	}
	
	
	@PostMapping("/mail")
	public String sendMail(@RequestParam String email, @RequestParam String subject, @RequestParam String message, ModelMap map)  {
		
		
//		try {
//			MimeMessage mimeMessage = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//			
//			helper.setFrom("shivampatanwar@gmail.com", "Shivam Patanwar");	
//			helper.setTo(email);
//			helper.setSubject(subject);
//			helper.setText(message);
//			
//			mailSender.send(mimeMessage);
//			
//			map.put("success", "Email send Successfully...");
//		}catch (Exception e) {
//			map.put("failure", "failed to send email...");
//		}
		
		
		
		
		
		// Godaddy mail
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			
			helper.setFrom("contact@shivampatanwar.com", "Shivam Patanwar Developer");	
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(message);
			
			mailSender.send(mimeMessage);
			
			map.put("success", "Email send Successfully Check email");
		}catch (Exception e) {
			map.put("failure", "failed to send email...");
		}
		
		
		
		 
		
		return "index";
	}

	
	
	
	@PostMapping("/otp")
	public String otp(@RequestParam String email, @RequestParam String subject, ModelMap map)  {
		
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			
			
			int otp = new Random().nextInt(100000, 1000000);
			
			
			
			String msg = "<body style='font-family: sans-serif; font-size:15px;'>" + 
			
								"Your onetime password for registration is  " + otp + "<br>" +
						        "Please use this OTP to complete your new user registration. " + "<br>" +
								"OTP is confidential, do not share this  with anyone." + "<br>  <br> <br> <br>" +
						        "<img src='https://firebasestorage.googleapis.com/v0/b/shivampatanwarapp.appspot.com/o/logo.jpeg?alt=media&token=402138fc-bf09-45ad-9938-40656f91901e'  alt='hello' width='300px'  height='80px'>" +
				
						 "</body>";
					
			
		
			mimeMessage.setContent(msg, "text/html");
			
			helper.setFrom("contact@shivampatanwar.com", "Shivam Patanwar Developer");	
			helper.setTo(email);
			helper.setSubject(subject); 
			
			mailSender.send(mimeMessage);
			
			map.put("success1", "OTP sent Successfully");
			map.put("otp", otp);
			
		}catch (Exception e) {
			map.put("failure1", "failed to send OTP...");
		}
		
		 
		
		return "index";
	}

	
	
	@PostMapping("/match")
	public String match(int mailotp, int otp, ModelMap map)  {
		
		if(mailotp==otp) {
			map.put("match", "OTP Match Success");
			map.put("otp", mailotp);
		}else {
			map.put("match", "Incorrect OTP");
			map.put("otp", mailotp);
		}
		
		return "index";
	}
	
	
	
	
	
	@PostMapping("/mobile")
	public String mobile(long mobile, ModelMap map)  {
		
		
		try {
			
			int otp = new Random().nextInt(1000, 10000);
			String  authorization = "Vpdrq45Msk0c6nDPz1bWxZ8wTeCNKlfQoEvYLgyAGXm2aFItj3WZXC3rtcYTjbKhfP8qVFEOyUNJQisS";
			String	route = "otp";
			String	variables_values = otp + "";
			String numbers = mobile + "";
			String	flash = "0";
			String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+authorization+"&route="+route+"&variables_values="+variables_values+"&flash="+flash+ "&numbers=" + numbers;
			
			
			
		
			
			URL url = new URL(myUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestProperty("cache-control", "no-cache");
			
			int code = connection.getResponseCode();
			System.out.println(code);
			
			map.put("res", "OTP sent Successfully");
			map.put("motp", otp);
			System.out.println(otp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "index";
	}
	
	
	
	@PostMapping("/motp")
	public String motp(int mailotp, int otp, ModelMap map)  {
		
		if(mailotp==otp) {
			map.put("match", "OTP Match Success");
			map.put("motp", mailotp);
		}else {
			map.put("match", "Incorrect OTP");
			map.put("motp", mailotp);
		}
		
		return "index";
	}
}
