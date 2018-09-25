package com.niit.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.backend.DAO.ProductDAO;
import com.niit.backend.model.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductDAO productDAO;
public ProductController(){
	System.out.println("ProductController Bean is Created");
}
@RequestMapping(value="/all/getallproducts")
public String getAllProducts(Model model){
	List<Product> products=productDAO.findAllProducts();
	//Attribute name is the Key - productList
	//value - List<Product> products is the data
	model.addAttribute("productsList",products);
	//                 
	return "listofproducts";//logical view name
	//in listofproducts.jsp,access the model attribute "productsList"
	
}
@RequestMapping(value="/admin/addproduct")
public String addProduct(@Valid @ModelAttribute(name="product") Product product,BindingResult result,Model model ,HttpServletRequest request )
{
	if(result.hasErrors()){//if it is true, result has errors
		model.addAttribute("categories",productDAO.findAllCategories());
		return "productform";
	}
	productDAO.addProduct(product);
	MultipartFile img=product.getImage();
	System.out.println(request.getServletContext().getRealPath("/"));

Path path=Paths.get(request.getServletContext().getRealPath("/")+"/resources/images/"+product.getProductId()+".jpg");
//transfer the image to the file
if(!img.isEmpty()&&img!=null){
	try {
		img.transferTo(new File(path.toString()));
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	return "redirect:/all/getallproducts";

}

@RequestMapping(value="/admin/deleteproduct/{ProductId}")
public String deleteProduct(@PathVariable int ProductId,Model model,HttpServletRequest request){
	productDAO.deleteProduct(ProductId);
	Path path=Paths.get(request.getServletContext().getRealPath("/")+"/resources/images/"+ProductId+".jpg");
	if(Files.exists(path)){
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return "redirect:/all/getallproducts";
}
@RequestMapping(value="/all/getproduct/{ProductId}")
public String getProduct(@PathVariable int ProductId,Model model){
	Product product=productDAO.findProductById(ProductId);
	model.addAttribute("productObj",product);
	return "viewproducts";
}
@RequestMapping(value="/admin/getproductform")
public String getproductform(Model model){
	Product p=new Product();
	model.addAttribute("product",p);
	model.addAttribute("categories",productDAO.findAllCategories());
	//In productform, access  the values of all the properties of product p
	//in the form, p.getId(),p.getProductname(),p.getPrice(),p.getQuantity(),p.getProductdesc()
	return "productform";
}
@RequestMapping(value="/admin/getupdateform/{ProductId}")
public String getUpdateProductForm(@PathVariable int ProductId,Model model){
	//how to get the product?
	Product product=productDAO.findProductById(ProductId);
	model.addAttribute("product",product);
	model.addAttribute("categories",productDAO.findAllCategories());
	return "updateproductform";
}
@RequestMapping(value="/admin/updateproduct")
public String updateProduct(@ModelAttribute Product product,BindingResult result,Model model,HttpServletRequest request){//product will have updated values
	   if(result.hasErrors()){
		   model.addAttribute("categories",productDAO.findAllCategories());
		   return "updateproductform";
	   }

	//product will have updated values
	productDAO.updateProduct(product);
	MultipartFile img=product.getImage();
	System.out.println(request.getServletContext().getRealPath("/"));
	//Defining a path
	Path path=Paths.get(request.getServletContext().getRealPath("/")+"/resources/images/"+product.getProductId()+".jpg");
	//transfer the image to the file
	if(!img.isEmpty()&&img!=null){
		try {
			img.transferTo(new File(path.toString()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return "redirect:/all/getallproducts";	
	}
@RequestMapping(value="/all/searchByCategory")
public String searchByCategory(@RequestParam String searchCondition ,Model model){
	if(searchCondition.equals("All"))
		model.addAttribute("searchCondition","");
	else
	model.addAttribute("searchCondition",searchCondition);
	model.addAttribute("productsList",productDAO.findAllProducts());
	return "listofproducts";
}
}


