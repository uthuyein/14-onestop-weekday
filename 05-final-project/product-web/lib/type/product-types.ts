import {z} from "zod"

export const productSchema = z.object({
     id: z.number().optional(),
     name: z.string().nonempty( "Product name is required"),
     isActive: z.boolean(),
     categoryId:z.number()
      
    })

export type ProductForm = z.infer<typeof productSchema>  

export type SearchProductForm = {
    isActive?:boolean
    keyword?:string
}

export type ProductListItem = {
    id:number
    name:string
    isActive:boolean
    category:{
        id:number
        name:string
        isActive:boolean
    }
}