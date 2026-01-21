"use server"
import {getProducts} from "@/lib/server/product.server"
import ProductPage from "./ProductPage"
import { getCategories } from "@/lib/server/category.server"

export default async function Page(){
    const products = await getProducts()
    const categories = await getCategories()
    
    return <ProductPage products={products} categories={categories} />
}