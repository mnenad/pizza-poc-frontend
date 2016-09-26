import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {LocationStrategy, HashLocationStrategy, APP_BASE_HREF} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AlertModule, DatepickerModule} from 'ng2-bootstrap/ng2-bootstrap';

import {routing, appRouterProviders} from './app.routing';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {OrderHistoryComponent} from "./orderhistory/orderhistory.component";
import {OrderComponent} from "./order/order.component";
import {LandingComponent} from "./landing/landing.component";
import {CheckoutComponent} from "./checkout/checkout.component";
@NgModule({
    declarations: [AppComponent,
        LandingComponent,
        HomeComponent,
        OrderComponent,
        OrderHistoryComponent,
        CheckoutComponent],
    imports: [BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
        AlertModule,
        routing],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [
        appRouterProviders,
        [{provide: APP_BASE_HREF, useValue: '/'}]
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
