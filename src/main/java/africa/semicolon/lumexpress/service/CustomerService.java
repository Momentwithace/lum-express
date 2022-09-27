package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CustomerLoginRequest;
import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dtos.response.CustomerRegisterResponse;
import africa.semicolon.lumexpress.data.dtos.response.LoginResponse;

public interface CustomerService {
    CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest);
    LoginResponse login(CustomerLoginRequest customerLoginRequest);
    String completeProfile(UpdateCustomerDetails updateCustomerDetials);

}
