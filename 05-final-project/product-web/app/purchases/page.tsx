"use server"

import { getProductPrices } from "@/lib/server/product.price.server"
import PurchasePage from "./PurchasePage"

export default async function Page(){
    const prices = await getProductPrices()
    
    return <PurchasePage prices={prices} />
}