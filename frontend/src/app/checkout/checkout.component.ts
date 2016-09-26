import {Component} from '@angular/core';
import {ViewEncapsulation} from '@angular/core'
import {LoginService} from "../service/login.service";
import {Router} from "@angular/router";
import {ConfigService} from "../service/config.service";
import {OrderService} from "../service/order.service";
import {OrderDetail} from "../model/orderDetail";
import {Headers, RequestOptions, Http} from "@angular/http";

@Component({
    selector: 'checkout',
    templateUrl: 'checkout.component.html',
    styleUrls: ['checkout.scss']
})
export class CheckoutComponent {

    loginService: LoginService;
    router: Router;
    configService: ConfigService;
    orderService: OrderService;
    http: Http;
    orderDetail:OrderDetail;

    constructor(loginService: LoginService, router: Router, orderService: OrderService, http: Http, configService: ConfigService) {

        this.loginService = loginService;
        this.router = router;
        this.orderService = orderService;
        this.http = http;
        this.configService = configService;
        this.orderDetail = new OrderDetail(
            "0",
            0,
            '',
            '',
            '',
            '',
            '',
            '',
            [],
            []
        );
    }

    checkout() {

        this.orderDetail.userId = this.loginService.getUser().userId;

        let body = JSON.stringify(this.orderDetail);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        this.http.post(this.configService.getBaseUrl() + '/order', body, options).toPromise()
            .then(response => {
                this.router.navigate(['/landing'])}
             )
            .catch(error => console.log(error));
    }

    // ngAfterViewInit() {
    //
    //     if(this.orderService.getOrderDetail() !== undefined){
    //         this.orderDetail = this.orderService.getOrderDetail();
    //     }
    //
    //
    //     // if (this.loginService.getUser() == null) {
    //     //     this.router.navigate(['/landing']);
    //     // }
    // }

    ngOnInit() {
        this.orderDetail = this.orderService.getOrderDetail();
    }


        // this.orderDetail = this.orderService.getOrderDetail();
        //
        // if (this.loginService.getUser() == null) {
        //     this.router.navigate(['/landing']);
        // }
    // }

    // ngOnDestroy() {
    //     this.orderService.setOrderDetailToEmpty();
    // }
}