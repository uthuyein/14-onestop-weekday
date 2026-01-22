import { z } from "zod";

export const categorySchema = z.object({
  id: z.number().optional(),
  name: z.string().nonempty( "Category name is required"),
  isActive: z.boolean(),
  subCategoryId:z.number().optional(),
});

export type CategoryForm = z.infer<typeof categorySchema>;


export type CategoryListItem ={
  id:number
  name: string 
  isActive:boolean
  subCategory:{
    id:number
    name: string 
    isActive:boolean
  }
}








