package org.sid.accountoperationservice.feign;

import org.sid.accountoperationservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceClient{
    @GetMapping(path="/customers/{id}")
    Customer getCustomerById(@PathVariable(name = "code") Long id);

    @GetMapping(path="/customers")
    PagedModel<Customer> pageClient(@RequestParam(name = "page")int page, @RequestParam(name = "size")int size);
}
