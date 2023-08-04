package th.mfu;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // select all product
    @GetMapping("/products")
    public Collection<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // select product by name
    @GetMapping("/products/name/{name}")
    public ResponseEntity getProductByName(@PathVariable String name) {
        List<Product> products = productRepository.findByNameStartingWith(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(products);
    }

    // creat new product
    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productRepository.save(product);

        // return craet success message
        return ResponseEntity.ok("Product had created");
    }

    // update
    @PutMapping("/products/")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        // check if id not exists
        if (!productRepository.existsById(product.getId())) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        productRepository.save(product);

        // return success message
        return ResponseEntity.ok("Product updated");
    }

    // delete
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        // check if id not exists
        if (!productRepository.existsById(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        productRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Product deleted");
    }

}
