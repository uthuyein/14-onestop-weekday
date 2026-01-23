import {z} from "zod"

export const productPriceSchema = z.object({
    id:z.number().optional(),
    categoryId:z.number(),
    productId :z.number().nonoptional("Please select one product !"),
    sizeId :z.number().nonoptional("Please select one size !"),
    priceType:z.string().nonempty("Please select one type !"),
    price :z.number(),
    createAt:z.date().optional(),
    

})

export type ProductPriceForm = z.infer<typeof productPriceSchema>

export type ProductPriceListItem = {
    id? :number,
    category:{
        id:number,
        name:string
    },
    product: {
        id:number,
        name:string
    },   
    size:{
        id:number,
        name:string
    },
    priceType: "Sales" | "Purchase"
,
    price:number,
    createAt?:string,
    updateAt?:string
}


