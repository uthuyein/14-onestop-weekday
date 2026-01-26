import {z} from "zod"

export const productPriceSchema = z.object({
    id:z.number().optional(),
    categoryId:z.number(),
    productId :z.number().nonoptional("Please select one product !"),
    sizeId :z.number().nonoptional("Please select one size !"),
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
    isActive:boolean,
    createAt?:Date,
    updateAt?:Date
}


