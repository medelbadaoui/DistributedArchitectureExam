import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../services/customer-service.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers:any;
  errorMessage:any;

  constructor(private customerssService:CustomersService) { }

  ngOnInit(): void {
    this.customerssService.getCustomers().subscribe(data=>{

      this.customers=data;
    },err=>{
      this.errorMessage=err.error.message;
      console.log(err);
    })

  }
  //public isCustomerManager():boolean{ return this.kcService.kc.hasRealmRole("CUSTOMER_MANAGER"); }

}

