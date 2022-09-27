package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CustomerLoginRequest;
import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dtos.response.CustomerRegisterResponse;
import africa.semicolon.lumexpress.data.dtos.response.LoginResponse;
import africa.semicolon.lumexpress.data.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    @Override
    public CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest) {
        return null;
    }

    @Override
    public LoginResponse login(CustomerLoginRequest customerLoginRequest) {
        return null;
    }

    @Override
    public String completeProfile(UpdateCustomerDetails updateCustomerDetials) {
        return null;
    }
}
