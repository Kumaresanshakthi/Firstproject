package com.niit.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.backend.DAO.CartItemDAO;
import com.niit.backend.DAO.ProductDAO;
import com.niit.backend.model.CartItem;

@Controller
public class HomeController
{
	@Autowired
	private ProductDAO productDao;
		@Autowired
		private CartItemDAO cartItemDao;
		
	public HomeController(){
		System.out.println("HomeController bean is instantiated");
	}

	@RequestMapping(value="/")
	public String homePage(HttpSession session,@AuthenticationPrincipal Principal principal){
	session.setAttribute("categories",productDao.findAllCategories());
	if(principal!=null){
	String email=principal.getName();
	List<CartItem> cartItems=cartItemDao.getCart(email);
	session.setAttribute("cartSize",cartItems.size());
	}
	return "home";
	}
	@RequestMapping(value="/login")

	public String getloginPage()
	{
		return"login";
	}
	@RequestMapping(value="/loginerror")
	public String loginFailed(Model model){
		model.addAttribute("error","Invalid credentials..");
		return "login";
	}
	@RequestMapping(value="/logout")
	public String logout(Model model){
		model.addAttribute("msg","Loggedout successfully...");
		return "login";
	}

	@RequestMapping(value="/Aboutus")

	public String getAboutusPage()
	{
		return"Aboutus";
	}
	@RequestMapping(value="/contactus")

	public String getcontactusPage()
	{
		return"contactus";
	}
	@RequestMapping(value="/footer")

	public String getfooterPage()
	{
		return"footer";
	}
	@RequestMapping(value="/header")

	public String getheaderPage()
	{
		return"header";
	}
}

