import {OrderDetail} from "./orderDetail";

export class Order {

    constructor(public orderId: string,
                public userId: string,
                public orderDetail: OrderDetail) {

    }
}
