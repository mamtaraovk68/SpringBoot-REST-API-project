package com.airbus.product.catelog.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbus.product.catelog.demo.exceptions.ProductManipulationException;
import com.airbus.product.catelog.demo.exceptions.TokenExpiresException;
import com.airbus.product.catelog.demo.exceptions.UserNotFoundException;
import com.airbus.product.catelog.demo.models.ProductDTO;
import com.airbus.product.catelog.demo.models.UpdateProductDTO;
import com.airbus.product.catelog.demo.service.ProductServiceImpl;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;

/**
 * @author mamtarao9395@gmail.com
 *
 */
@RestController
@RequestMapping("/myapp/product")
public class ProductCatalogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogController.class);

	@Autowired
	ProductServiceImpl productService;
	
	
	
	/**
	 * Method to list all the products in the catalog
	 * @param request
	 * @return
	 * @throws UserNotFoundException 
	 * @throws TokenExpiresException 
	 */
	@GetMapping("/getAll")
	public List<ProductDTO> viewAllProducts(@RequestHeader("AUTH_HEADER_TOKEN") String token) throws UserNotFoundException, TokenExpiresException{
		LOGGER.info("getAll product api called with token: " +token );
		return  productService.viewAllProduct(token);	
		
	}
	
	
	
	/**'Method to list all the product with a particular category
	 * @param category
	 * @param request
	 * @return
	 * @throws TokenExpiresException 
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/get/{category}")
	public  Iterable<ProductDTO> viewProduct(@PathVariable String category, @RequestHeader("AUTH_HEADER_TOKEN") String token) throws UserNotFoundException, TokenExpiresException{
		LOGGER.info("getAll with category api called with token: " +token );
		return productService.viewProduct(category,token);
	
	}
	
	/**
	 * Method to add a new product in the catalog
	 * @param productDTO
	 * @param request
	 * @return
	 * @throws TokenExpiresException 
	 * @throws UserNotFoundException 
	 * @throws ProductManipulationException 
	 */
	@PostMapping("/add")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO addProductDTO, @RequestHeader("AUTH_HEADER_TOKEN") String token) throws UserNotFoundException, TokenExpiresException, ProductManipulationException {
		LOGGER.info("add product api called with token: " +token);
		ProductDTO productDTO=  productService.addProduct(addProductDTO, token);
		
		return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
		
	}
	
	/**
	 * Method to update the existing product in the catalog
	 * @param productId
	 * @param request
	 * @return
	 * @throws ProductManipulationException 
	 * @throws TokenExpiresException 
	 * @throws UserNotFoundException 
	 */
	@PutMapping("/update/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable String productId, @RequestBody UpdateProductDTO updateProductDTO, @RequestHeader("AUTH_HEADER_TOKEN") String token) throws UserNotFoundException, TokenExpiresException, ProductManipulationException {
		LOGGER.info("update product api called with token: " +token );
		ProductDTO productDTO=  productService.updateProduct(productId,updateProductDTO, token);
		
		return new ResponseEntity<>(productDTO , HttpStatus.CREATED);
	}

}
