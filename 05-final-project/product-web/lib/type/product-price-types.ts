import {z} from "zod"

export const productPriceSchema = z.object({
    id:z.number().optional(),
    categoryId: z.string().min(1, "Category is required"),
    productId: z.string().min(1, "Product is required"),
    sizeId: z.string().min(1, "Size is required"),
    priceType:z.string().nonempty("Please select one type !"),
    createAt:z.date() .optional(),
    updateAt:z.date() .optional(),
    isActive:z.boolean(),
    price : z.coerce.number().min(1)

})

export type ProductPriceForm = z.infer<typeof productPriceSchema>

export type SearchProductPriceForm = {
    keyword?:string
}



export type SelectProductPrice = {
    id :number,
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





