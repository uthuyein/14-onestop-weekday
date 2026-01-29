import {z} from "zod"

export const productPriceSchema = z.object({
    id:z.number().optional(),
    categoryId :z.number(),
    productId :z.number(),
    sizeId :z.number(),
    priceType:z.string().nonempty("Please select one type !"),
    createAt:z.date() .optional(),
    updateAt:z.date() .optional(),
    isActive:z.boolean(),
    price : z.number().optional()
})

export type ProductPriceForm = z.infer<typeof productPriceSchema>

export type SearchProductPriceForm = {
    keyword?:string
}



export type SelectProductPrice = {
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
    priceType:string
,
    price:number,
    createAt:Date,
    updateAt:Date
}





