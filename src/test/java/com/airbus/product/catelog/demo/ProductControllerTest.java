package com.airbus.product.catelog.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.airbus.product.catelog.demo.controller.ProductCatalogController;
import com.airbus.product.catelog.demo.models.Product;
import com.airbus.product.catelog.demo.models.ProductDTO;
import com.airbus.product.catelog.demo.repositories.ProductRepository;

@RunWith(SpringRunner.class)
public class ProductControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
    ProductCatalogController productCatalogController;
	
    @Mock
    ProductRepository productRepository;
    
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(productCatalogController)
                .build();
    }
    
    @Test
    public void getAllProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product("001","P01","Helicopter","H60","Helicopter H60", 8) , new Product("002","P02","Helicopter","H61","Helicopter H61", 9));
        Mockito.when(productRepository.findAll()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllItems"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    
    
}
