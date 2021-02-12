import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CustomersComponent } from './customers/customers.component';
import { DetailCustomerComponent } from './detail-customer/detail-customer.component';

const routes: Routes = [
  { path: "customers",component: CustomersComponent},
  { path: "customers/create",component: CreateCustomerComponent},
  { path: "customers/:id",component: DetailCustomerComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
