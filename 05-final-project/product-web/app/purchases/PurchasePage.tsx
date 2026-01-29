"use client"

import { useForm, useFieldArray } from "react-hook-form"
import { ProductPriceTable } from "./product-price-table"
import { PurchaseDetailTable } from "./purchase-detail-table"
import { ProductPrice, Supplier } from "@/lib/type/purchase-types"

type PurchaseDetailForm = {
  productPrice: ProductPrice
  qty: number
}

type PurchaseForm = {
  issueDate: string
  supplier: Supplier
  purchaseDetails: PurchaseDetailForm[]
}

export default function PurchaseCreatePage() {
  const form = useForm<PurchaseForm>({
    defaultValues: {
      issueDate: new Date().toISOString().substring(0, 10),
      purchaseDetails: [],
    },
  })

  const { control, handleSubmit, watch } = form

  const { fields, append, remove, update } = useFieldArray({
    control,
    name: "purchaseDetails",
  })

  const onSubmit = (data: PurchaseForm) => {
    console.log(data)
    // POST to /purchase
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="grid grid-cols-2 gap-4">
      <ProductPriceTable onSelect={(pp) => addProduct(pp)} />
      <PurchaseDetailTable
        fields={fields}
        update={update}
        remove={remove}
      />
    </form>
  )

  function addProduct(productPrice: ProductPrice) {
    const exists = fields.find(
      (f) => f.productPrice.id === productPrice.id
    )
    if (exists) return

    append({ productPrice, qty: 1 })
  }
}
