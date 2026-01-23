"use server"

import { SizeForm } from "../type/size-type";
import { POST_CONFIG, PUT_CONFIG } from "../utils";
import { request } from "./base.server";

const  ENDPOINT ="admin/sizes"

export async function getSizes() {
  const res = await request(`${ENDPOINT}`)
  if (!res.ok) return [];
  return res.json();
}

export async function updateSize(id:number ,form :SizeForm){
     const response = await request(`${ENDPOINT}/${id}`, {
          ... PUT_CONFIG,
            body:JSON.stringify(form)
        })

    if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Update failed: ${errorText}`);
  }
}

export async function createProduct(form :SizeForm){
     const response = await request(`${ENDPOINT}`, {
          ... POST_CONFIG,
            body:JSON.stringify(form)
        })

    if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Create failed: ${errorText}`);
  }
}