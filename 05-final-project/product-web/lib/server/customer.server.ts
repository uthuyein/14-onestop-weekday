
"use server";

import { revalidatePath } from "next/cache";
import { request } from "./base.server";
import { POST_CONFIG, PUT_CONFIG } from "../utils";
import { CustomerForm } from "../type/customer-types";

const  ENDPOINT ="member/customers"

export async function getCustomers() {
  const res = await request(`${ENDPOINT}`)
  if (!res.ok) return [];
  return res.json();
}

export async function createCustomers(data: CustomerForm) {
  const response = await request(`${ENDPOINT}`, {
      ... POST_CONFIG,
        body:JSON.stringify(data)
    })  
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Create failed: ${errorText}`);
  }

  revalidatePath(ENDPOINT); 
  return { success: true };
}

 export async function updateCustomer(id: number, data: CustomerForm) { 

     const response = await request(`${ENDPOINT}/${id}`, {
      ... PUT_CONFIG,
        body:JSON.stringify(data)
    }) 
 
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Update failed: ${errorText}`);
  }

  revalidatePath(ENDPOINT); 
  return { success: true };
}
