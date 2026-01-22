import { getProductPrices } from "@/lib/server/product.price.server";
import ProductPricePage from "../productPrices/ProductPricePage";

export default async  function Page () {
    const prices = await getProductPrices();
    return <ProductPricePage prices = {prices} />
}