import {z} from "zod"

export const productSchema = z.object({
     id: z.number().optional(),
     name: z.string().nonempty( "Product name is required"),
     isActive: z.boolean(),
     categoryId:z.number().min(1),
     categoryName:z.string()   
    })

export type ProductForm = z.infer<typeof productSchema>  