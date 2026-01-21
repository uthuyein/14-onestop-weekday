"use server"
import { ProductForm } from "../type/product-types";
import { POST_CONFIG, PUT_CONFIG } from "../utils";
import { request } from "./base.server";

const  ENDPOINT ="member/products"

export async function getProducts() {
  const res = await request(`${ENDPOINT}`)
  if (!res.ok) return [];
  return res.json();
}

export async function updateProduct(id:number ,form :ProductForm){
     const response = await request(`${ENDPOINT}/${id}`, {
          ... PUT_CONFIG,
            body:JSON.stringify(form)
        })

    if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Update failed: ${errorText}`);
  }
}

export async function createProduct(form :ProductForm){
     const response = await request(`${ENDPOINT}`, {
          ... POST_CONFIG,
            body:JSON.stringify(form)
        })

    if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Create failed: ${errorText}`);
  }
}




