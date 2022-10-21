package africa.semicolon.lumexpress.service;


import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dtos.response.RegisterResponse;
import africa.semicolon.lumexpress.data.models.Customer;

import java.util.List;


public interface CustomerService {
    RegisterResponse register(CustomerRegisterRequest customerRegisterRequest);

    String updateCustomerProfile(UpdateCustomerDetails updateCustomerDetails);
    List<Customer> getAllCustomers();

}
