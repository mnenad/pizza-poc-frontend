import {Component} from '@angular/core';
import {Http, Response} from "@angular/http";
import {OrderDetail} from "../model/orderDetail";
import {Router} from "@angular/router";
import {OrderService} from "../service/order.service";
import {LoginService} from "../service/login.service";
import {ConfigService} from "../service/config.service";

@Component({
    selector: 'home',
    templateUrl: 'home.component.html',
    styleUrls: ['home.scss']
})
export class HomeComponent {

    orderDetails: OrderDetail[];
    router: Router;
    orderService: OrderService;
    loginService: LoginService;
    http: Http;
    configService: ConfigService;

    constructor(http: Http, router: Router, orderService: OrderService, loginService: LoginService, configService: ConfigService) {

        this.router = router;
        this.orderService = orderService;
        this.loginService = loginService;
        this.http = http;
        this.configService = configService;
    }

    ngAfterViewInit() {

        this.orderService.setOrderDetail(undefined);

        if (this.loginService.getUser() == null) {
            this.router.navigate(['/landing']);
        }

        this.http.get(this.configService.getBaseUrl() + '/premade')
            .map((res: Response) => res.json())
            .subscribe(
                data => {
                    this.orderDetails = data
                },
                err => console.error(err),
                () => {
                    console.log('done', this.orderDetails)
                }
            );
    }

    startOrder(orderDetail: OrderDetail) {

        if (orderDetail == undefined) {
            this.orderService.setOrderDetailToEmpty();
        } else {
            this.orderService.setOrderDetail(orderDetail);
        }

        this.router.navigate(['/order']);
    }
}



