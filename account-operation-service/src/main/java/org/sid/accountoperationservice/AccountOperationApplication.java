package org.sid.accountoperationservice;

import org.sid.accountoperationservice.repository.AccountRepository;

import org.sid.accountoperationservice.repository.OperationRepository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


import java.util.Date;

import org.sid.accountoperationservice.entities.Operation;
import org.sid.accountoperationservice.entities.Account;
import org.sid.accountoperationservice.feign.*;


@SpringBootApplication
@EnableFeignClients
public class AccountOperationApplication {

	public static void main(String[] args) {
        SpringApplication.run(AccountOperationApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            AccountRepository compteRepository,
            OperationRepository operationRepository,
            CustomerServiceClient clientRestClient
    ){
        return args -> {

            clientRestClient.pageClient(0,20).forEach(client->{
                Account compte = new Account();
                compte.setSolde(2000.);
                compte.setEtat("COURANT");
                compte.setType("ACTIVE");
                compte.setDateCreation(new Date());
                compte.setCustomerID(client.getId());
                compteRepository.save(compte);
                System.out.println(client.getId());
            });
            compteRepository.findAll().forEach(compte -> {
                Operation operation = new Operation();
                operation.setDate(new Date());
                operation.setType("DEBIT");
                operation.setCompte(compte);
                operation.setMontant(200.);
                compte.setSolde(compte.getSolde()-200);
                compteRepository.save(compte);
                operationRepository.save(operation);
                System.out.println(compte.getId());
            });
        };
    }
	
}



