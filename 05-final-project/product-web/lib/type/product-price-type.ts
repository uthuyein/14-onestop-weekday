import {z} from "zod"

export const productPriceSchema = z.object({
    productId :z.number().nonoptional("Please select one product !"),
    sizeId :z.number().nonoptional("Please select one size !"),
    priceType:z.string().nonempty("Please select one type !"),
    price :z.number()

})

export type ProductPriceForm = z.infer<typeof productPriceSchema>

export type ProductPriceListItem = {
    id? :number,
    product: {
        id:number,
        name:string
    }
    category:{
        id:number,
        name:string
    },
    size:{
        id:number,
        name:string
    },
    priceType:string,
    createAt:Date,
    updateAt:Date
}


