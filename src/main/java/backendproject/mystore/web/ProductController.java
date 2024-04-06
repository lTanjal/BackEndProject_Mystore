package backendproject.mystore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.security.core.Authentication;

import backendproject.mystore.domain.AppUser;
import backendproject.mystore.domain.AppUserRepository;
import backendproject.mystore.domain.Photos;
import backendproject.mystore.domain.PhotosRepository;
import backendproject.mystore.domain.Product;
import backendproject.mystore.domain.ProductRepository;
import backendproject.mystore.domain.Status;
import backendproject.mystore.domain.StatusRepository;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductRepository prepository;

	@Autowired
	private StatusRepository srepository;

	@Autowired
	private PhotosRepository phrepository;

	@Autowired
	private AppUserRepository arepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public String productList(Model model, Authentication authentication) {
		Iterable<Product> iterableProducts = prepository.findAll();
		List<Product> productsList = new ArrayList<>();
		List<Product> filteredProducts = new ArrayList<>();
		iterableProducts.forEach(productsList::add);

		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		if (isAdmin) {
			filteredProducts.addAll(productsList);
		} else {
			// If the user is not an admin, filter products by status "New"
			for (Product product : productsList) {
				if (product.getStatus().getStatus().equals("New")) {
					filteredProducts.add(product);
				}
			}
		}

		model.addAttribute("products", filteredProducts);
		return "productlist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long id, Model model) {
		prepository.deleteById(id);
		return "redirect:../productlist";

	}

	@RequestMapping(value = "/buyproduct/{id}", method = RequestMethod.GET)
	public String buyProduct(@PathVariable("id") Long id, Model model) {
		Optional<Product> product = prepository.findById(id);

		Status statuscheck = srepository.findByStatus("Process");
		model.addAttribute("statuscheck", statuscheck);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		AppUser currentUserId = arepository.findByUserName(currentUserName);
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("productToCard", product);
		return "buyproduct";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Product product) {
		prepository.save(product);
		logger.info("findPRODUCT {}", product);
		return "redirect:/productlist";
	}

	
	@RequestMapping(value = "/shoppingcard", method = RequestMethod.GET)
	public String shoppingList(Model model) {
		Iterable<Product> iterableProducts = prepository.findAll();
		List<Product> productsList = new ArrayList<>();
		List<Product> filteredProducts = new ArrayList<>();

		iterableProducts.forEach(productsList::add);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		AppUser currentUserId = arepository.findByUserName(currentUserName);
		logger.info("findUSERNAME: {}", currentUserId.getCmId());
		for (Product product : productsList) {
			if (product.getAppUser().getCmId().equals(currentUserId.getCmId())) {
				filteredProducts.add(product);
			}
		}
		model.addAttribute("products", filteredProducts);
		return "shoppingcard";
	}

	@RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable("id") Long id, Model model) {
		Optional<Product> productOptional = prepository.findById(id);
		Product product = productOptional.get();
		List<Photos> productPhotos = product.getPhotos();
		model.addAttribute("productToEdit", product);
		model.addAttribute("statuses", srepository.findAll());
		model.addAttribute("productBuyer", arepository.findAll());
		model.addAttribute("productPhotos", productPhotos);
		return "editproduct";
	}

	@RequestMapping(value = "/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("newproduct", new Product());
		model.addAttribute("statuses", srepository.findAll());
		model.addAttribute("productBuyer", arepository.findAll());
		return "addproduct";
	}

	@RequestMapping(value = "/addphotos/{id}", method = RequestMethod.GET)
	public String addPhotos(@PathVariable("id") Long id, Model model) {
		Optional<Product> productOptional = prepository.findById(id);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();

			Photos newPhoto = new Photos();
			newPhoto.setProduct(product);

			model.addAttribute("newphotos", newPhoto);
			model.addAttribute("product", product);

			return "addphotos";
		} else {

			return "error"; // For example
		}
	}

	@RequestMapping(value = "/savePhoto", method = RequestMethod.POST)
	public String savePhoto(@ModelAttribute("newphotos") Photos newPhoto) {
		phrepository.save(newPhoto);
		logger.info("Photos SAVED {}", newPhoto);
		return "redirect:productlist";
	}

	@RequestMapping(value = "/deletePhoto/{productId}/{photoId}", method = RequestMethod.GET)
	public String deletePhoto(@PathVariable("productId") Long productId,
			@PathVariable("photoId") Long photoId,
			RedirectAttributes redirectAttributes) {
			phrepository.deleteById(photoId);
			redirectAttributes.addAttribute("id", productId);
        return "redirect:/editproduct/{id}";
    } 
	
}
