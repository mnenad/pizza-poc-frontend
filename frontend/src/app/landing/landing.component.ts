import {Component} from '@angular/core';
import {ViewEncapsulation} from '@angular/core'
import {LoginService} from "../service/login.service";
import {LoginRequest} from "../model/loginrequest";
import {Router} from "@angular/router";
import {ConfigService} from "../service/config.service";

declare const FB: any;

@Component({
    selector: 'landing',
    templateUrl: 'landing.component.html',
    styleUrls: ['landing.scss']
})
export class LandingComponent {

    loginService: LoginService;
    router: Router;
    configService:ConfigService;

    constructor(loginService: LoginService, router: Router, configService:ConfigService) {

        this.loginService = loginService;
        this.router = router;
        this.configService = configService;

        FB.init({
            appId: this.configService.getAppId(),
            cookie: false,  // enable cookies to allow the server to access
            // the session
            xfbml: true,  // parse social plugins on this page
            version: 'v2.5' // use graph api version 2.5
        });

        FB.getLoginStatus(response => {
            this.statusChangeCallback(response);
        });
    }

    statusChangeCallback(resp) {
        if (resp.status === 'connected') {
            console.log('FB_CONNECTED', resp)

            let loginRequest:LoginRequest = new LoginRequest();
            loginRequest.authToken = resp.authResponse.accessToken;

            this.loginService.signInOrCreateAccount(loginRequest);
        } else if (resp.status === 'not_authorized') {
            console.log('FB_NOT_AUTH', resp)
        } else {
            console.log('FB_ERROR', resp)
        }
    };
}