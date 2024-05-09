package com.mruruc;


import com.mruruc.appFactory.CustomerRepositoryFactory;
import com.mruruc.model.Customer;
import com.mruruc.repository.CustomerRepository;

public class App {
    public static void main(String[] args) {

        CustomerRepository customerRepository = CustomerRepositoryFactory.getCustomerRepository();

        /*
        Customer customer = new Customer();
        customer.setFullName("John Doe");
        customer.setCity("New York");
        customer.setEmail("john@email.com");
        customer.setPhone("123456789");


        int i = customerRepository.saveCustomer(customer);
        System.out.println(i);
*/


        //customerRepository.findAllCustomers().forEach(System.out::println);

//        customerRepository.findCustomer(45)
//                .ifPresent(System.out::println);

        System.out.println(customerRepository.deleteCustomer(5));
    }
}
