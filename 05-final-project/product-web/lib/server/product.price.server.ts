"use server"
import { ProductPriceForm, SelectProductPrice ,SearchProductPriceForm} from "../type/product-price-types";
import { GET_CONFIG, POST_CONFIG, PUT_CONFIG } from "../utils";
import { request } from "./base.server";

const ENDPOINT = "admin/prices"

export async function getProductPrices():Promise<SelectProductPrice[]>{
     const res = await request(`${ENDPOINT}`)
      if (!res.ok) return [];
      return res.json();
}

export async function updateProductPrice(id:number ,form :ProductPriceForm){
     const response = await request(`${ENDPOINT}/${id}`, {
          ... PUT_CONFIG,
            body:JSON.stringify(form)
        })

    if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Update failed: ${errorText}`);
  }
}

export async function createProductPrice(form :ProductPriceForm){
     const response = await request(`${ENDPOINT}`, {
          ... POST_CONFIG,
            body:JSON.stringify(form)
        })

    if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Create failed: ${errorText}`);
  }

}

export async function findProductPrices(
        form: SearchProductPriceForm
      ): Promise<SelectProductPrice[]> {

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
