
"use server";

import { revalidatePath } from "next/cache";
import { request } from "./base.server";
import { CategoryForm } from "../type/category-types";
import { POST_CONFIG, PUT_CONFIG } from "../utils";

const  ENDPOINT ="admin/categories"

export async function getCategories() {
  const res = await request(`${ENDPOINT}`)
  if (!res.ok) return [];
  return res.json();
}

export async function createCategory(data: CategoryForm) {
  const response = await request(`${ENDPOINT}`, {
      ... POST_CONFIG,
        body:JSON.stringify(data)
    })  
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Create failed: ${errorText}`);
  }

  revalidatePath("/categories"); 
  return { success: true };
}

 export async function updateCategory(id: number, data: CategoryForm) { 

     const response = await request(`${ENDPOINT}/${id}`, {
      ... PUT_CONFIG,
        body:JSON.stringify(data)
    }) 
 
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Update failed: ${errorText}`);
  }

  revalidatePath("/categories"); 
  return { success: true };
}
