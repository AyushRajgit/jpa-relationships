package in.cper.database.ECommerceOrderEngine.service;

import in.cper.database.ECommerceOrderEngine.entity.Customer;
import in.cper.database.ECommerceOrderEngine.exceptions.EmptyRequestException;
import in.cper.database.ECommerceOrderEngine.exceptions.NotFoundException;
import in.cper.database.ECommerceOrderEngine.exceptions.UnableToSaveException;
import in.cper.database.ECommerceOrderEngine.repository.RegisterCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterCustomerService {

    private RegisterCustomerRepository registerCustomerRepository;

    @Autowired
    public RegisterCustomerService(RegisterCustomerRepository registerCustomerRepository) {
        this.registerCustomerRepository = registerCustomerRepository;
    }

    @Transactional
    public void register(Customer customer) {
        Customer saved = registerCustomerRepository.save(customer);
        if (saved == null) {
            throw new UnableToSaveException("User unable to save in database");
        }
    }

    @Transactional
    public void registerAll(List<Customer> customers) {
        if (customers == null || customers.size() == 0) {
            throw new EmptyRequestException("Empty user list while registering");
        }

        for (Customer customer : customers) {
            register(customer);
        }
    }

    public Customer get(int id) {
        Customer customer = registerCustomerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Unable to fetch user with userId : " + id)
        );

        return customer;
    }

    public List<Customer> getAll() {
        List<Customer> customers = registerCustomerRepository.findAll();
        if (customers == null) {
            throw new NotFoundException("Unable to fetch users");
        }
        return customers;
    }

    @Transactional
    public void remove(int id) {
        Customer customer = registerCustomerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Unable to fetch user with userId : " + id + " while deleting")
        );

        registerCustomerRepository.delete(customer);
    }
}
