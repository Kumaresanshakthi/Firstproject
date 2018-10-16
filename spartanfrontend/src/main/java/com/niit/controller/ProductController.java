package com.niit.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.niit.model.Category;
import com.niit.model.Product;
import com.niit.model.Supplier;
import com.niit.userdao.CategoryDAO;
import com.niit.userdao.ProductDAO;
import com.niit.userdao.SupplierDAO;

@Controller
public class ProductController {
	@Autowired
	private ProductDAO productDao;

    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    SupplierDAO supplierDAO;
    
    
    
    
    
public ProductController(){
	System.out.println("ProductController Bean is Created");
}
@RequestMapping(value="/all/getallproducts")
public String getAllProducts(Model model){
	List<Product> products=productDao.findAllProduct();
	//Attribute name is the Key - productList
	//value - List<Product> products is the data
	model.addAttribute("productsList",products);
	//                 
	return "listofproducts";//logical view name
	//in listofproducts.jsp,access the model attribute "productsList"
	
}
@RequestMapping(value="/all/getproduct/{id}")
public String getProduct(@PathVariable int id,Model model)
{
	Product product=productDao.findProductById(id);
	model.addAttribute("productObj",product);
	return "viewproduct";
}
@RequestMapping(value="/admin/deleteproduct/{id}")
public String deleteProduct(@PathVariable int id,Model model,HttpServletRequest request){
	productDao.deleteProduct(id);
	Path path=Paths.get(request.getServletContext().getRealPath("/")+"/resources/images/"+id+".jpg");
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
@RequestMapping(value="/admin/getproductform")
public String getproductform(Model model){
	Product p=new Product();
	model.addAttribute("product",p);
	model.addAttribute("category",productDao.findAllCategory());
	//In productform, access  the values of all the properties of product p
	//in the form, p.getId(),p.getProductname(),p.getPrice(),p.getQuantity(),p.getProductdesc()
	return "productform";
}
@RequestMapping(value="/admin/addproduct")
public String addProduct(@Valid @ModelAttribute(name="product") Product product,BindingResult result,Model model ,HttpServletRequest request  ){
	if(result.hasErrors()){//if it is true, result has errors
		model.addAttribute("category",productDao.findAllCategory());
		return "productform";
	}
	productDao.addProduct(product);
	MultipartFile img=product.getImage();
	System.out.println(request.getServletContext().getRealPath("/"));
	Path path=Paths.get(request.getServletContext().getRealPath("/")+"/resources/images/"+product.getId()+".jpg");
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
@RequestMapping(value="/admin/getupdateform/{id}")
public String getUpdateProductForm(@PathVariable int id,Model model){
	//how to get the product?
	Product product=productDao.findProductById(id);
	model.addAttribute("product",product);
	model.addAttribute("category",productDao.findAllCategory());
	return "updateproductform";
}
@RequestMapping(value="/admin/updateproduct")
public String updateProduct(@Valid @ModelAttribute Product product,BindingResult result,Model model,HttpServletRequest request){//product will have updated values
	if(result.hasErrors()){
		   model.addAttribute("category",productDao.findAllCategory());
		   return "updateproductform";
	   }
	productDao.updateProduct(product);
	MultipartFile img=product.getImage();
	System.out.println(request.getServletContext().getRealPath("/"));
	//Defining a path
	Path path=Paths.get(request.getServletContext().getRealPath("/")+"/resources/images/"+product.getId()+".jpg");
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
	model.addAttribute("productsList",productDao.findAllProduct());
	return "listofproducts";
	
}
/*
 *
 * 
 * 
 *                                              CATEGORY
 * 
 * 
 * 
 * 
 * 
 * 
*/


@RequestMapping(value="/all/getcategoryform")
public ModelAndView getCategoryForm(Model model)
{
model.addAttribute("categories",categoryDAO.findAllCategory());
return new ModelAndView("Category");

}



@ModelAttribute("Category")
public Category getCategory()
{
	return new Category();
}
@RequestMapping(value="/admin/addcategory")
public String addCategory(@ModelAttribute("Category")Category category)
{
	
categoryDAO.addCategory(category);	
return "redirect:/all/getallcategory";
}

@RequestMapping(value="/all/getallcategory")
public String getAllCategory(Model model){
	List<Category> category=categoryDAO.findAllCategory();
	//Attribute name is the Key - productList
	//value - List<Product> products is the data
	model.addAttribute("categoryList",category);
	//                 
	return "listofcategory";
}

@RequestMapping(value="/admin/deletecategory/{categoryId}")
public String deleteCategory(@PathVariable int categoryId,Model model,HttpServletRequest request){
	categoryDAO.deleteCategory(categoryId);
	//delete from cartItem where cartitemid=?
	return "redirect:/all/getallcategory";
	
	//select * from cartitem where user_email=? - to execute the query redirect /cart/getcart
}

/*

*
*
*                                       SUPPLIER
*
*
*
*
*
*
*/

@RequestMapping(value="/all/getsupplierform")
public ModelAndView getSupplierForm(Model model)
{
	model.addAttribute("suppliers",supplierDAO.findAllSuppliers());
	return new ModelAndView("Supplier");
	
}


@ModelAttribute("supplier")
public Supplier getSupplier()
{
	return new Supplier();
}

@RequestMapping(value="/admin/addsupplier")
public String addSupplier(@ModelAttribute("Supplier")Supplier supplier)

{
	
supplierDAO.addSupplier(supplier);	
return "redirect:/all/getallsupplier";
}

@RequestMapping(value="/all/getallsupplier")
public String getAllsupplier(Model model){
	List<Supplier> products=supplierDAO.findAllSuppliers();
	//Attribute name is the Key - productList
	//value - List<Product> products is the data
	model.addAttribute("supplierList",products);
	//                 
	return "listofsupplier";
}
@RequestMapping(value="/admin/deletesupplier/{supplierId}")
public String deleteSupplier(@PathVariable int supplierId,Model model,HttpServletRequest request){
	supplierDAO.deleteSupplier(supplierId);
	//delete from cartItem where cartitemid=?
	return "redirect:/all/getallsupplier";
	
	//select * from cartitem where user_email=? - to execute the query redirect /cart/getcart
}

}