import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomersComponent } from './customers/customers.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { DetailCustomerComponent } from './detail-customer/detail-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    CreateCustomerComponent,
    DetailCustomerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
