// app/product-price/product-price-search-form.tsx
"use client"

import { useForm } from "react-hook-form"
import { Form} from "@/components/ui/form"
import { Button } from "@/components/ui/button"

import FormSelect from "@/components/forms/form-select"
import { getProductPrices } from "@/lib/server/product.price.server"
import { useEffect, useState } from "react"

export type SearchProductPriceForm = {
  category?: string
  product?: string
  size?:string
  priceType?:"Sale" | "Purchase"
  dateFrom?:Date
  dateTo?:Date

}


export default function ProductPriceSearchForm({onSearch,}: {onSearch: (data: SearchProductPriceForm) => void}) {
  const form = useForm<SearchProductPriceForm>()
  
  const [categories, setCategories] = useState<{ key: string; value: string }[]>([])
  const [products, setProducts] = useState<{ key: string; value: string }[]>([])

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
  }
  loadPrices()
}, [])
  
  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSearch)} className="flex gap-4">
        <FormSelect control={form.control} options={categories} path="category" label="Category" />
         <FormSelect control={form.control} options={products} path="product" label="Product" />

        <Button type="submit" className="self-end">
          Search
        </Button>
        
      </form>
    </Form>
  )
}
