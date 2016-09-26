

export class DeleteOrderRequest {

    constructor(public fbAuthToken: string,
                public orderId: number | string) {

    }
}
