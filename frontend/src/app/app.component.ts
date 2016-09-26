import {Component, ViewEncapsulation} from '@angular/core';
import {OrderService} from "./service/order.service";
import {LoginService} from "./service/login.service";
import {ConfigService} from "./service/config.service";
import {Router} from "@angular/router";
import {LoginRequest} from "./model/loginrequest";

declare const FB: any;

@Component({
    selector: 'spring-boot-angular2',
    templateUrl: 'app.component.html',
    styleUrls: ['app.scss'],
    providers: [OrderService, LoginService, ConfigService],
    encapsulation: ViewEncapsulation.None
})
export class AppComponent {

    private router: Router;
    public showNav: boolean;
    private loginService: LoginService;

    public showHomeNav: boolean = false;
    public showLoginFbNav: boolean = true;
    public showLogoutFbNav: boolean = false;
    public showHistoryNav: boolean = false;

    public pageHeader: string = "";
    public pageSubTitle: string = "";

    constructor(router: Router, loginService: LoginService) {
        this.router = router;
        this.showNav = true;
        this.loginService = loginService;

        this.router.events.subscribe(path => {
            console.log('path = ', path);

            this.configNavigation(path);
        });
    }

    onFacebookLogoutClick() {
        FB.logout(response => {
            this.router.navigate(['/landing']);
        });
    }

    onFacebookLoginClick() {
        FB.login(response => {
            this.statusChangeCallback(response);
        });
    }

    statusChangeCallback(resp) {
        if (resp.status === 'connected') {
            console.log('FB_CONNECTED', resp)

            let loginRequest: LoginRequest = new LoginRequest();
            loginRequest.authToken = resp.authResponse.accessToken;

            this.loginService.signInOrCreateAccount(loginRequest);

            this.showLoginFbNav = false;
            this.showLogoutFbNav = true;

        } else if (resp.status === 'not_authorized') {
            console.log('FB_NOT_AUTH', resp)

            this.showLoginFbNav = true;
            this.showLogoutFbNav = false;
        } else {
            console.log('FB_ERROR', resp)

            this.showLoginFbNav = true;
            this.showLogoutFbNav = false;
        }
    };

    configNavigation(path: any) {
        console.log('page', path.urlAfterRedirects);

        if (path.urlAfterRedirects === '/home') {
            this.showHomeNav = false;
            this.showHistoryNav = true;

            this.pageHeader = "Our Pizzas";
            this.pageSubTitle = "";

        } else if (path.urlAfterRedirects === '/landing' || path.urlAfterRedirects === '/') {
            this.showHistoryNav = false;

            this.pageHeader = "Feeling Hungry?";
            this.pageSubTitle = "Please login with Facebook to begin your pizza experience";
        } else if (path.urlAfterRedirects === '/order') {
            this.showHistoryNav = true;

        } else if (path.urlAfterRedirects === '/orderhistory') {
            this.showHistoryNav = true;
            this.showHistoryNav = false;

        } else if (path.urlAfterRedirects === '/checkout') {
            this.showHistoryNav = true;
            this.showHistoryNav = false;
        }
    }
}
