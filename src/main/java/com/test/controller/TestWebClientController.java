package com.test.controller;

import com.test.dto.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class which is used to
 *
 * @author Tarun Rohila
 */
@RestController
public class TestWebClientController {
    
    private static final Logger LOGGER = LogManager.getLogger(TestWebClientController.class);
    private WebClient webClient;

    public TestWebClientController() {
        webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
    }



    @GetMapping("/test")
    public String test() throws InterruptedException {
        TestWebClientController testWebClientController = new TestWebClientController();
        testWebClientController.createNewProduct()
                .thenMany(testWebClientController.getAllProduct())
                .flatMap(p -> testWebClientController.updateProduct(p.getId(), "Shampoo"))
                .flatMap(p -> testWebClientController.deleteProduct(p.getId()))
                .thenMany(testWebClientController.getAllProduct())
        .subscribe(System.out::println);

        //Thread.sleep(10000);
        return "test";
    }

    public Mono<ResponseEntity<Product>> createNewProduct() {
        return webClient.post()
                .uri("/products")
                .body(Mono.just(new Product(1, "Soap")), Product.class)
                .exchangeToMono(clientResponse -> clientResponse.toEntity(Product.class))
                .doOnSuccess( p -> System.out.println(" Add a new PRODUCT" + p));

    }

    public Flux<Product> getAllProduct() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnNext( p -> System.out.println(" GET ALL PRODUCTS" + p));

    }

    public Mono<Product> updateProduct(long id, String name) {
        return webClient.put().uri("/products/{id}", id)
                .body(Mono.just(new Product(id, name)), Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnSuccess(p -> System.out.println("Update a product" + p));
    }

    public Mono<Void> deleteProduct(long id) {
        return webClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(p -> System.out.println("Delete a product"));
    }
}
