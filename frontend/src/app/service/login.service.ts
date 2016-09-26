import {Component, Injectable} from '@angular/core';
import {Http, Response, RequestOptions, Headers} from "@angular/http";
import {User} from "../model/user";
import {LoginRequest} from "../model/loginrequest";
import {Router} from "@angular/router";
import {ConfigService} from "./config.service";

@Injectable()
export class LoginService {

    private http: Http;
    private user: User;
    private router: Router;
    private configService: ConfigService;

    constructor(http: Http, router: Router, configService: ConfigService) {
        this.http = http;
        this.router = router;
        this.configService = configService;
    }

    signInOrCreateAccount(loginRequest: LoginRequest) {

        let body = JSON.stringify(loginRequest);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        this.http.post(this.configService.getBaseUrl() + '/login', body, options).toPromise()
            .then(response => {
                console.log('USER RESPONSE', response);

                let user: User = new User();
                user.userId = response.json().userId;
                user.userEmail = response.json().userEmail;
                user.authToken = loginRequest.authToken;

                this.user = user
                console.log('USER RESPONSE OBJECT', this.user);

                if (!(this.user === undefined)) {
                    this.router.navigate(['/home']);
                }
            })
            .catch(error => console.log(error));
    }

    getUser() {
        if (this.user === undefined) {
            return null;
        } else {
            return this.user;
        }
    }
}


