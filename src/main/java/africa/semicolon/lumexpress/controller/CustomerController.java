package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import africa.semicolon.lumexpress.exception.LumExpressException;
import africa.semicolon.lumexpress.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerRegisterRequest request) throws LumExpressException {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(request));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
