package com.nineleaps.expresssample.controllers;

import com.nineleaps.expresssample.entities.Product;
import com.nineleaps.expresssample.exceptions.NotFoundException;
import com.nineleaps.expresssample.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.nineleaps.expresssample.utils.Utils.asJsonString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Test
    void createProductShouldCreateANewProduct() throws Exception {
        long testId = 1234;
        Product inputProduct = new Product(null, "sample product", 20.4, 4, "description");
        Product testProduct = new Product(testId, "sample product", 20.4, 4, "description");
        when(productService.createProduct(inputProduct)).thenReturn(testProduct);

        mockMvc.perform(post("/product")
                .content(asJsonString(inputProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1234)))
                .andExpect(jsonPath("$.name", is("sample product")))
                .andExpect(jsonPath("$.price", is(20.4)))
                .andExpect(jsonPath("$.inventory", is(4)))
                .andExpect(jsonPath("$.description", is("description")));

    }

    @Test
    void getProductShouldReturnValidProductWithJsonAnd200() throws Exception {
        long testId = 1234;
        Product testProduct = new Product(testId, "sample product", 20.4, 4, "description");
        when(productService.getProduct(testId)).thenReturn(testProduct);

        mockMvc.perform(get("/product/1234"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1234)))
                .andExpect(jsonPath("$.name", is("sample product")))
                .andExpect(jsonPath("$.price", is(20.4)))
                .andExpect(jsonPath("$.inventory", is(4)))
                .andExpect(jsonPath("$.description", is("description")));
    }

    @Test
    void createInvalidProductShouldReturnReturnErrorWithMessage422() throws Exception {
        long testId = 1234;
        Product inputProduct = new Product(null, null, 20.4, 4, "description");
        Product testProduct = new Product(testId, "sample product", 20.4, 4, "description");
        when(productService.createProduct(inputProduct)).thenReturn(testProduct);

        mockMvc.perform(post("/product")
                .content(asJsonString(inputProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getInvalidProductIdShouldReturn404() throws Exception {
        long testId = 34545;
        when(productService.getProduct(testId)).thenThrow(new NotFoundException("Product not found"));

        mockMvc.perform(get("/product/34545"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Product not found")));
    }
    @Test
    void shouldUpdateAValidProduct() throws Exception {
        long testId = 1234;
        Product inputProduct = new Product(null, "sample product1", 20.4, 4, "description");
        Product testProduct = new Product(testId, "sample product1", 20.4, 4, "description");
        when(productService.updateProduct(testId, inputProduct)).thenReturn(testProduct);

        mockMvc.perform(put("/product/1234")
                .content(asJsonString(inputProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1234)))
                .andExpect(jsonPath("$.name", is("sample product1")))
                .andExpect(jsonPath("$.price", is(20.4)))
                .andExpect(jsonPath("$.inventory", is(4)))
                .andExpect(jsonPath("$.description", is("description")));
    }

    @Test
    void updateInvalidProductShouldReturnReturnErrorWithMessage422() throws Exception {
        long testId = 1234;
        Product inputProduct = new Product(null, null, 20.4, 4, "description");
        Product testProduct = new Product(testId, "sample product", 20.4, 4, "description");
        when(productService.createProduct(inputProduct)).thenReturn(testProduct);

        mockMvc.perform(put("/product/1234")
                .content(asJsonString(inputProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateInvalidProductIdShouldReturnReturnErrorWithMessage404() throws Exception {
        long testId = 1234;
        Product inputProduct = new Product(null, "sample product", 20.4, 4, "description");
        Product testProduct = new Product(testId, "sample product", 20.4, 4, "description");
        when(productService.updateProduct(testId,inputProduct)).thenThrow(new NotFoundException("Product not found"));

        mockMvc.perform(put("/product/1234")
                .content(asJsonString(inputProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Product not found")));
    }

    @Test
    void deleteProductShouldDelete() throws Exception {
        long testId = 1234;
        Product testProduct = new Product(testId, "sample product", 20.4, 4, "description");
        doNothing().when(productService).deleteProduct(testId);

        mockMvc.perform(delete("/product/1234"))
                .andExpect(status().isNoContent());
    }
}