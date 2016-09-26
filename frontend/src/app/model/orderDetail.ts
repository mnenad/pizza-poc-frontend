export class OrderDetail {

    constructor(
        public userId: number | string,
        public orderId: number | string,
        public premadeName: string,
        public crustStyle: string,
        public size: string,
        public baseSauce: string,
        public cheese: string,
        public orderDate: string,
        public meats: string[],
        public otherToppings: string[]
    ) {  }
}