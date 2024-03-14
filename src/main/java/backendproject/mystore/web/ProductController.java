package backendproject.mystore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import backendproject.mystore.domain.PhotosRepository;
import backendproject.mystore.domain.ProductRepository;
import backendproject.mystore.domain.StatusRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository prepository;

    @Autowired
    private StatusRepository srepository;

    @Autowired
    private PhotosRepository phrepository;

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    @RequestMapping(value = "/productlist")
	public String productList(Model model) {
		model.addAttribute("products", prepository.findAll());
		return "productlist";
	}

 


}
