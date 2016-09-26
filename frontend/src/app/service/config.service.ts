import {Injectable} from "@angular/core";

@Injectable()
export class ConfigService {

    //DEV SETUP
    // private baseUrl: string = "http://localhost:8081/";
    // private appId: string = "1309813052376049";

    //PRODUCTION SETUP
    private baseUrl: string = "http://atbpizzabackend.cfapps.io";
    private appId: string = "1022551641176891";

    getBaseUrl() {
        return this.baseUrl;
    }

    getAppId(){
        return this.appId;
    }
}