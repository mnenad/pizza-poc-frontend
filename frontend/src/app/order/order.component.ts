import {Component, ElementRef, Inject} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {OrderDetail} from "../model/orderDetail";
import {LoginService} from "../service/login.service";
import {Router} from "@angular/router";
import {OrderService} from "../service/order.service";
import {Toppings} from "./toppings";
import {ConfigService} from "../service/config.service";

@Component({
    selector: 'order',
    templateUrl: 'order.component.html',
    styleUrls: ['order.scss']
})
export class OrderComponent {

    private http: Http;
    public toppings: Toppings;
    private loginService: LoginService;
    private router: Router;
    private orderService: OrderService;
    private configService: ConfigService;
    model:OrderDetail;

    //init the model


    constructor(http: Http, orderService: OrderService, loginService: LoginService, router: Router, configService: ConfigService) {

        this.toppings = new Toppings();
        this.http = http;
        this.loginService = loginService;
        this.router = router;
        this.orderService = orderService;
        this.configService = configService;

        this.model = new OrderDetail(
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

    updateCheckedOtherTopping(option, event) {

        var index = this.model.otherToppings.indexOf(option);
        if (event.target.checked) {
            if (index === -1) {
                this.model.otherToppings.push(option);
            }
        } else {
            // console.log('remove');
            if (index !== -1) {
                this.model.otherToppings.splice(index, 1);
            }
        }
    }

    updateCheckedMeats(option, event) {

        var index = this.model.meats.indexOf(option);
        if (event.target.checked) {
            if (index === -1) {
                this.model.meats.push(option);
            }
        } else {
            console.log('remove');
            if (index !== -1) {
                this.model.meats.splice(index, 1);
            }
        }
    }

    ngAfterViewInit() {

        console.log('OrderComponent loginService.getUser()', this.loginService.getUser())

        if (this.loginService.getUser() == null) {
            this.router.navigate(['/landing']);
        }

        this.getToppings();
    }

    getToppings() {

        this.http.get(this.configService.getBaseUrl() + '/toppings')
            .map((res: Response) => res.json())
            .subscribe(
                data => {
                    this.toppings = data;

                    this.model.premadeName = this.orderService.getOrderDetail().premadeName;
                    this.model.size = this.orderService.getOrderDetail().size;
                    this.model.crustStyle = this.orderService.getOrderDetail().crustStyle;
                    this.model.baseSauce = this.orderService.getOrderDetail().baseSauce;
                    this.model.cheese = this.orderService.getOrderDetail().cheese;
                    this.model.otherToppings = this.orderService.getOrderDetail().otherToppings;
                    this.model.meats = this.orderService.getOrderDetail().meats;
                },
                err => console.error(err),
                () => {
                    console.log('done1', this.toppings)
                }
            );
    }

    public navigateToCheckout() {

            this.orderService.setOrderDetail(this.model);
            this.router.navigate(['/checkout']);

    }

    // ngOnDestroy(){
    //     this.orderService.setOrderDetailToEmpty();
    // }
}
