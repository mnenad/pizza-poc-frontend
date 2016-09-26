import {Component, Injectable} from '@angular/core';
import {OrderDetail} from "../model/orderDetail";

@Injectable()
export class OrderService {

    private orderDetail: OrderDetail;

    constructor() {

        let order: OrderDetail = new OrderDetail(
            "0", "0", "", "", "", "", "", "", [], []
        );

        this.orderDetail = order;
    }

    getOrderDetail():OrderDetail{
        return this.orderDetail;
    }

    setOrderDetail(orderDetail : OrderDetail){
        this.orderDetail = orderDetail;
    }

    setOrderDetailToEmpty(){
        let order: OrderDetail = new OrderDetail(
            "0", "0", "", "", "", "", "", "", [], []
        );

        this.orderDetail = order;
    }
}


