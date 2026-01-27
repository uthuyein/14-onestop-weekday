"use server"
import { getProductPrices } from "@/lib/server/product.price.server";
import ProductPricePage from "@/app/productPrices/edit/ProductPriceEdit";
import { getSizes } from "@/lib/server/size.server";
import { getCategories } from "@/lib/server/category.server";
import { getProducts } from "@/lib/server/product.server";

export default async  function Page () {
    const products = await getProducts();
    const categories = await getCategories()
    const prices = await getProductPrices();
    const sizes = await getSizes();

    return <ProductPricePage categories={categories}  products = {products} prices = {prices}  sizes={sizes}/>
}
