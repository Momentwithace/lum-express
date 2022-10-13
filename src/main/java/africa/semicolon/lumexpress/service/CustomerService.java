package africa.semicolon.lumexpress.service;


import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dtos.response.RegisterResponse;


public interface CustomerService {
    RegisterResponse register(CustomerRegisterRequest customerRegisterRequest);

    String completeProfile(UpdateCustomerDetails updateCustomerDetails);

}
