// app/product-price/product-price-search-form.tsx
"use client"

import { useForm } from "react-hook-form"
import { Form} from "@/components/ui/form"
import { Button } from "@/components/ui/button"

import FormSelect from "@/components/forms/form-select"
import { getProductPrices } from "@/lib/server/product.price.server"
import { useEffect, useState } from "react"
import { SearchProductPriceForm } from "@/lib/type/product-price-types"


export default function ProductPriceSearchForm({onSearch,}: {onSearch: (data: SearchProductPriceForm) => void}) {
  
  const form = useForm<SearchProductPriceForm>()
  const [categories, setCategories] = useState<{ key: string; value: string }[]>([])
  const [products, setProducts] = useState<{ key: string; value: string }[]>([])
  const [sizes, setSizes] = useState<{ key: string; value: string }[]>([])

  useEffect(() => {
  async function loadPrices() {
    const prices = await getProductPrices()

    setCategories(
      Array.from(
        new Map(
          prices.map(p => [
            p.category.id,
            {
              key: p.category.id.toString(),
              value: p.category.name,
            },
          ])
        ).values()
      )
    )

    setProducts(
      Array.from(
        new Map(
          prices.map(p => [
            p.product.id,
            {
              key: p.product.id.toString(),
              value: p.product.name,
            },
          ])
        ).values()
      )
    )

    setSizes(
      Array.from(
        new Map(
          prices.map(s => [
            s.size.id,
            {
              key: s.size.id.toString(),
              value: s.size.name,
            },
          ])
        ).values()
      )
    )
  }
  loadPrices()
}, [])
  
  return (
    <div className="w-full space-y-5 p-5">
        <div className="flex space-x-1 items-center">       
          <h2 className="text-lg  "> Product Price List</h2>
        </div>
        <Form {...form}>
        <form onSubmit={form.handleSubmit(onSearch)} className="flex gap-4">
            <FormSelect control={form.control} options={categories} path="category" label="Category" />
            <FormSelect control={form.control} options={products} path="product" label="Product" />
            <FormSelect control={form.control} options={sizes} path="size" label="Size" />

            <Button type="submit" className=" bg-blue-500 hover:bg-blue-800 self-end">
            Search
            </Button>
            
        </form>
        </Form>
    </div>
  )
}
