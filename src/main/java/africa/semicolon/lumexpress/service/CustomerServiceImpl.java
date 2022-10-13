package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.request.EmailNotificationRequest;
import africa.semicolon.lumexpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dtos.response.RegisterResponse;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.repository.CustomerRepository;
import africa.semicolon.lumexpress.service.notification.EmailNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper = new ModelMapper();

    private final EmailNotificationService emailNotificationService;

    private final VerificationTokenService verificationTokenService;

    @Override
    public RegisterResponse register(CustomerRegisterRequest customerRegisterRequest) {
        Customer customer = mapper.map(customerRegisterRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(customerRegisterRequest, customer);
        Customer savedCustomer = customerRepository.save(customer);
        var token = verificationTokenService.createToken(savedCustomer.getEmail());
        emailNotificationService.sendHtmlMail(buildEmailNotificationRequest(token, savedCustomer.getFirstName()));
        return registrationResponseBuilder(savedCustomer);
    }

    private String getEmailTemplate(){
        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("/home/ace/Downloads/spring boot files/lum-express/src/main/resources/welcome.txt"))) {
            return bufferedReader.lines().collect(Collectors.joining());
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    private void setCustomerAddress(CustomerRegisterRequest customerRegisterRequest, Customer customer) {
        Address customerAddress = new Address();
        customerAddress.setCountry(customerRegisterRequest.getCountry());
        customer.getAddress().add(customerAddress);
    }

    private EmailNotificationRequest buildEmailNotificationRequest(VerificationToken verificationToken, String customerName) {
        var email = getEmailTemplate();
        String mail = null;
        if(email != null){
            mail = String.format(email, verificationToken.getUserEmail(), "http://localhost:8080/api/v1/customer/verify/" + verificationToken.getToken());
        }
       return EmailNotificationRequest.builder()
                .userEmail(verificationToken.getUserEmail())
                .mailContent(mail)
                .build();
    }

    private RegisterResponse registrationResponseBuilder(Customer customer){
        return RegisterResponse.builder()
                .message("Success")
                .userId(customer.getId())
                .code(201)
                .build();

    }


    @Override
    public String completeProfile(UpdateCustomerDetails updateCustomerDetails) {
        return null;
    }
}
