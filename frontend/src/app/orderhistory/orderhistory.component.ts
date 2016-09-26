import {Component} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {LoginService} from "../service/login.service";
import {Router} from "@angular/router";
import {OrderDetail} from "../model/orderDetail";
import {Order} from "../model/order";
import {ConfigService} from "../service/config.service";
import {OrderService} from "../service/order.service";
import {DeleteOrderRequest} from "./request/deleteOrderRequest";

@Component({
    selector: 'orderhistory',
    templateUrl: 'orderhistory.component.html',
    styleUrls: ['orderhistory.scss']
})
export class OrderHistoryComponent {
    private loginService: LoginService;
    private router: Router;
    private http: Http;
    // private orderDetails: OrderDetail[];
    private orders: Order[];
    private configService: ConfigService;
    private orderService: OrderService;

    constructor(http: Http, loginService: LoginService, router: Router, configService: ConfigService, orderService: OrderService) {
        this.loginService = loginService;
        this.router = router;
        this.http = http;
        // this.orderDetails = [];
        this.orders = [];
        this.configService = configService;
        this.orderService = orderService;
    }

    ngAfterViewInit() {

        console.log('OrderHistoryComponent loginService.getUser()', this.loginService.getUser())

        if (this.loginService.getUser() == null) {
            this.router.navigate(['/landing']);
        } else {
            this.http.get(this.configService.getBaseUrl() + '/getOrders/' + this.loginService.getUser().userId)
                .map((res: Response) => res.json())
                .subscribe(
                    data => {

                        this.orders = data;

                        // for (var order of this.orders) {
                        //     console.log('REDE', order)
                        //     this.orderDetails.push(order.orderDetail);
                        // }
                    },
                    err => console.error(err),
                    () => {
                        // console.log('done', this.user)
                    }
                );
        }
    }

    startOrder(orderDetail: OrderDetail) {

        if (orderDetail == undefined) {
            this.orderService.setOrderDetailToEmpty();
        } else {
            this.orderService.setOrderDetail(orderDetail);
        }

        this.router.navigate(['/order']);
    }


    //delete order

    deleteOrder(order:Order, event){
        event.stopPropagation();
        let deleteOrderRequest:DeleteOrderRequest = new DeleteOrderRequest(this.loginService.getUser().authToken, order.orderId);
        let body = JSON.stringify(deleteOrderRequest);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        var index = this.orders.indexOf(order);
        this.http.post(this.configService.getBaseUrl() + 'deleteorder', body, options).toPromise()
            .then(response => {
                if (index != -1) {
                    this.orders.splice(index, 1);
                }

                // let user: User = new User();
                // user.userId = response.json().userId;
                // user.userEmail = response.json().userEmail;
                //
                // this.user = user
                // console.log('USER RESPONSE OBJECT', this.user);
                //
                // if (!(this.user === undefined)) {
                //     this.router.navigate(['/home']);
                // }
            })
            .catch(error => console.log(error));
    }

}
