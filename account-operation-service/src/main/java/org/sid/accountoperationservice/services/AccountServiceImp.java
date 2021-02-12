package org.sid.accountoperationservice.services;

import org.sid.accountoperationservice.entities.Account;
import org.sid.accountoperationservice.entities.Operation;
import org.sid.accountoperationservice.feign.CustomerServiceClient;
import org.sid.accountoperationservice.repository.AccountRepository;
import org.sid.accountoperationservice.repository.OperationRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Date;

@RestController
@Transactional
public class AccountServiceImp implements IAccountService{

    final AccountRepository compteRepository;
    final OperationRepository operationRepository;
    final CustomerServiceClient clientRestClient;

    public AccountServiceImp(AccountRepository compteRepository,OperationRepository operationRepository, CustomerServiceClient clientRestClient) {
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
        this.clientRestClient = clientRestClient;
    }

    @PostMapping(path = "/add-compte")
    Account addCompte(Account c){
        compteRepository.save(c);
        return c;
    }

    @PostMapping(path = "/versement")
    Account addVersement(Operation o){
        o.setType("DEBIT");
        o.setDate(new Date());
        operationRepository.save(o);
        Account c = compteRepository.findById(o.getCompte().getId()).get();
        c.setSolde(c.getSolde()+o.getMontant());
        compteRepository.save(c);
        return c;
    }
    @PostMapping(path = "/retrait")
    Account addRetrait(Operation o){
        o.setType("CREDIT");
        o.setDate(new Date());
        operationRepository.save(o);
        Account c = compteRepository.findById(o.getCompte().getId()).get();
        c.setSolde(c.getSolde()-o.getMontant());
        compteRepository.save(c);
        return c;
    }
    @PostMapping(path = "/comptetocompte")
    void addRetrait(@RequestParam(name = "compte_debiteur") Account compteDebiteur ,
                      @RequestParam(name = "compte_crediteur") Account compteCrediteur,
                      @RequestParam(name = "montant") double montant
                      ){
        Operation opCredit = new Operation(null,montant,new Date(),"DEBIT",compteDebiteur);
        Operation opDebit = new Operation(null,montant,new Date(),"CREDIT",compteCrediteur);
        compteDebiteur.setSolde(compteDebiteur.getSolde()+montant);
        compteCrediteur.setSolde(compteCrediteur.getSolde()-montant);
        operationRepository.save(opCredit);
        operationRepository.save(opDebit);
        compteRepository.save(compteDebiteur);
        compteRepository.save(compteCrediteur);
    }

    @GetMapping(path = "/getOpetration")
    Collection<Operation> addRetrait(@RequestParam(name = "compte") Long compte_id){
        Account c  = compteRepository.findById(compte_id).get();
        return c.getOperations();
    }
    @GetMapping(path = "/consulter-compte")
    Account consulterCompte(@RequestParam(name = "compte") Long compte_id){
        Account c  = compteRepository.findById(compte_id).get();
        c.setCustomer(clientRestClient.getCustomerById(c.getId()));

        return c;

    }

    @PostMapping(path = "/toggle-compte")
    Account toggleCompte(@RequestParam(name = "compte") Long compte_id){
        Account c  = compteRepository.findById(compte_id).get();
        if(c.getEtat().equals("SUSPENDED"))
            c.setEtat("ACTIVE");
        else if(c.getEtat().equals("ACTIVE"))
            c.setEtat("SUSPENDED");
        return c;

    }
}
    


