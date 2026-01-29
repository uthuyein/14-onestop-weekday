import {z} from "zod"

export const productPriceSchema = z.object({
    id:z.number().optional(),
    categoryId :z.preprocess(
            (v) => (v === "" ? undefined : Number(v)),
            z.number().positive("Price must be greater than 0")
            ),
    productId :z.preprocess(
            (v) => (v === "" ? undefined : Number(v)),
            z.number().positive("Price must be greater than 0")
            ),
    sizeId :z.preprocess(
            (v) => (v === "" ? undefined : Number(v)),
            z.number().positive("Price must be greater than 0")
            ),
    priceType:z.string().nonempty("Please select one type !"),
    createAt:z.date() .optional(),
    udpateAt:z.date() .optional(),
    isActive:z.boolean(),
    price : z.preprocess(
            (v) => (v === "" ? undefined : Number(v)),
            z.number().positive("Price must be greater than 0")
            )
})

export type ProductPriceForm = z.infer<typeof productPriceSchema>

export type SearchProductPriceForm = {
    category?:string,
    product?:string,
    size?:string
    priceType?:string
    dateFrom?:Date
    dateTo?:Date
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





