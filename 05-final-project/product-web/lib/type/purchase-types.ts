import { z } from "zod"

export const purchaseDetailSchema = z.object({
  productPrice: z.object({
    id: z.number().positive()
  }),
  qty: z.number().min(1, "Qty must be at least 1"),

  // UI-only
  productName: z.string(),
  price: z.number()
})

// export const purchaseFormSchema = z.object({
//   issueDate: z.string().min(1, "Issue date required"),

//   supplier: z.object({
//     id: z.number()
//   }),

//   purchaseDetails: z
//     .array(purchaseDetailSchema)
//     .min(1, "Please create at least one product purchase!")
// })

// export type PurchaseForm = z.infer<typeof purchaseFormSchema>


export type ProductPrice = {
  id: number
  price: number
  isActive: boolean
  product: Product
}

export type Product = {
  id: number
  name: string
  isActive: boolean
}

export type Supplier = {
  id: number
  name: string
}

export type PurchaseDetailForm = {
  productPrice: ProductPrice
  qty: number
}


export type PurchaseForm = {
  issueDate: string
  supplier: Supplier
  purchaseDetails: PurchaseDetailForm[]
}


