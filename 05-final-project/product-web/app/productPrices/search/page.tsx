// app/product-price/page.tsx
"use client"

import { useState } from "react"
import ProductPriceSearchForm, {
  SearchProductPriceForm,
} from "@/app/productPrices/search/ProductPriceSearch"
import { findProductPrices } from "@/lib/server/product.price.server"
import { SelectProductPriceList } from "@/lib/type/product-price-types"
import ProductPriceTable from "@/components/forms/tables/table-product-price"

export default  function ProductPricePage() {
   
  const [productPrices, setProductPrices] =  useState<SelectProductPriceList[]>([])

  const handleSearch = async (form: SearchProductPriceForm) => {
    const params = {
      ...form,  
        category: form.category,
        product: form.product,
        size: form.size,
        priceType: form.priceType,
        dateFrom: form.dateFrom ,
        dateTo: form.dateTo 
       
    }

    

    const result = await findProductPrices(params)
  
   console.log("API Result:", result) 
    setProductPrices(result)
  }

  return (
    <div className="space-y-4">
      <ProductPriceSearchForm onSearch={handleSearch} />
      <ProductPriceTable prices={productPrices} onEdit={(row) => console.log("edit", row)}  onDelete={(row) => console.log("delete", row)}/>

      <pre className="bg-gray-100 p-2 rounded">
        {JSON.stringify(productPrices, null, 2)}
      </pre>
    </div>
  )
}
