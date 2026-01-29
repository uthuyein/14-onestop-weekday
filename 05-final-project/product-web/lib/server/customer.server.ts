
"use server";

import { revalidatePath } from "next/cache";
import { request } from "./base.server";
import { GET_CONFIG, POST_CONFIG, PUT_CONFIG } from "../utils";
import { CustomerForm, SearchCustomer, SelectCustomer } from "../type/customer-types";

const  ENDPOINT ="member/customers"

export async function getCustomers() {
  const res = await request(`${ENDPOINT}`)
  if (!res.ok) return [];
  return res.json();
}

export async function deactivateCustomer(id:number) {
    const response = await request(`${ENDPOINT}/${id}/deactivate`, {
    method: "PUT", 
  });
 
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Update failed: ${errorText}`);
  }

  revalidatePath(ENDPOINT); 
  return { success: true };
}

export async function findCustomers(
        form: SearchCustomer
      ): Promise<SelectCustomer[]> {

        const params = new URLSearchParams()

        Object.entries(form).forEach(([key, value]) => {
          if (value !== undefined && value !== "") {
            params.append(key, String(value))
          }
        })

        const response = await request(`${ENDPOINT}/find?${params.toString()}`, {
          ... GET_CONFIG,    
        })

      if (!response.ok) {
          const errorText = await response.text();
          throw new Error(`Create failed: ${errorText}`);
        }


  return response.json()
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


