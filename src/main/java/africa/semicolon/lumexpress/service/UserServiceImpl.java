package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumexpress.data.dtos.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.Admin;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.data.models.Vendor;
import africa.semicolon.lumexpress.data.repository.AdminRepository;
import africa.semicolon.lumexpress.data.repository.CustomerRepository;
import africa.semicolon.lumexpress.data.repository.VendorRepository;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Customer> customer = customerRepository.findByEmail(loginRequest.getEmail());
        if(customer.isPresent() && customer.get().getPassword().equals(loginRequest.getPassword())) return buildLoginResponse(customer.get());
        Optional<Admin> admin = adminRepository.findByEmail(loginRequest.getEmail());
        if(admin.isPresent() && admin.get().getPassword().equals(loginRequest.getPassword())) return buildLoginResponse(admin.get());
        Optional<Vendor> vendor = vendorRepository.findByEmail(loginRequest.getEmail());
        if(vendor.isPresent() && vendor.get().getPassword().equals(loginRequest.getPassword())) return  buildLoginResponse(vendor.get());

        return LoginResponse.builder()
                .code(400)
                .message("Login failed, Bad credentials")
                .build();
    }

    @Override
    public LumExpressUser getUserByUsername(String email) {
        Optional<Admin> foundAdmin =
                adminRepository.findByEmail(email);
        if(foundAdmin.isPresent()) return foundAdmin.get();

        Optional<Customer> foundCustomer =
                customerRepository.findByEmail(email);
        if(foundCustomer.isPresent()) return foundCustomer.get();

        Optional<Vendor> foundVendor =
                vendorRepository.findByEmail(email);
        if(foundVendor.isPresent()) return foundVendor.get();

        throw  new UserNotFoundException("User Not Found!!");
    }


    private LoginResponse buildLoginResponse(LumExpressUser customer) {
        return LoginResponse.builder()
                .message("User Login Successfully")
                .code(200)
                .build();
    }
}
