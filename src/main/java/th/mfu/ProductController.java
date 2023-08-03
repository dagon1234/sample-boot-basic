package th.mfu;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    // creat hashmap for employee
    private HashMap<Long, Product> productDB = new HashMap<Long, Product>();

    // select all product
    @GetMapping("/products")
    public Collection<Product> getAllProduct() {
        return productDB.values();
    }

    // creat new product
    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        // check if id exists
        if (productDB.containsKey(product.getId())) {
            // return error message
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee id already exists");
        }

        // add produch to hashmap
        productDB.put(product.getId(), product);

        // return craet success message
        return ResponseEntity.ok("Product had created");
    }

}
