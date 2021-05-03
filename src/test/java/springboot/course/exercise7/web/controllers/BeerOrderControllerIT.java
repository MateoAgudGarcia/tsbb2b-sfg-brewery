package springboot.course.exercise7.web.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import springboot.course.exercise7.domain.Customer;
import springboot.course.exercise7.repositories.BeerOrderRepository;
import springboot.course.exercise7.repositories.CustomerRepository;
import springboot.course.exercise7.services.BeerOrderService;
import springboot.course.exercise7.services.BeerService;
import springboot.course.exercise7.web.model.BeerDto;
import springboot.course.exercise7.web.model.BeerOrderPagedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerOrderControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    CustomerRepository customerRepository;

    Customer customer   ;

    @BeforeEach
    void setUp() {
        customer = customerRepository.findAll().get(0);
    }

    @AfterEach
    void tearDown() {
        reset(customerRepository);
    }

    @Test
    void testListOrders() {
        String url = "/api/v1/customers/"+customer.getId().toString()+"/orders";

        BeerOrderPagedList pagedList = testRestTemplate.getForObject(url, BeerOrderPagedList.class);

        assertThat(pagedList.getContent()).hasSize(1);
//        then(pagedList.getContent()).should()
    }
}