import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {HomeComponent} from './home/home.component';
import {OrderHistoryComponent} from "./orderhistory/orderhistory.component";
import {OrderComponent} from "./order/order.component";
import {LandingComponent} from "./landing/landing.component";
import {CheckoutComponent} from "./checkout/checkout.component";

const appRoutes: Routes = [
    {path: '', component: LandingComponent},
    {path: 'landing', component: LandingComponent},
    {path: 'home', component: HomeComponent},
    {path: 'order', component: OrderComponent},
    {path: 'orderhistory', component: OrderHistoryComponent},
    {path: 'checkout', component: CheckoutComponent}
];

export const appRouterProviders: any[] = [];

export const routing: ModuleWithProviders =
    RouterModule.forRoot(appRoutes);
