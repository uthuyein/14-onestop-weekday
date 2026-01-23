"use server"
import { getProductPrices } from "@/lib/server/product.price.server";
import ProductPricePage from "../productPrices/ProductPricePage";
import { getSizes } from "@/lib/server/size.server";

export default async  function Page () {
    const prices = await getProductPrices();
    const sizes = await getSizes();

    return <ProductPricePage prices = {prices}  sizes={sizes}/>
}
