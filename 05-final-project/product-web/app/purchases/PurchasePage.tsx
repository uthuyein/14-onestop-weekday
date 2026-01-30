"use client"

import FormSelect from "@/components/forms/form-select";
import  { SelectProductPriceTable } from "@/components/forms/tables/table-product-price";
import { Button } from "@/components/ui/button";
import { Form } from "@/components/ui/form";
import { ProductPriceForm, productPriceSchema, SelectProductPrice } from "@/lib/type/product-price-types";
import { OptionItem } from "@/lib/type/types";
import { zodResolver } from "@hookform/resolvers/zod";
import { Plus, Save } from "lucide-react"
import { useForm, UseFormReturn } from "react-hook-form";

export type PurchaseProps ={
    form:UseFormReturn<ProductPriceForm>,
    prices:SelectProductPrice[],
    isEditMode:boolean,
    categoryOptions:OptionItem[],
    productOptions:OptionItem[],
    sizeOptions:OptionItem[]
}

export default function PurchasePage({form,prices,isEditMode,categoryOptions,productOptions,sizeOptions}:PurchaseProps){
   

    return (
      <div className="w-full space-y-5 p-5">
        <div className="flex space-x-1 items-center">
          { isEditMode ? <Save className="w-5 h-5 " /> : <Plus className="w-5 h-5" />}
          <h2 className="text-lg  ">
            {isEditMode ? "Update ":"Create"} Product Form</h2>
        </div>
       
          <Form {...form}>
              {/* <form onSubmit={form.handleSubmit(onsubmit)} className="space-y-4"> */}
                  <div className="flex items-end space-x-3">
            
                  <FormSelect className="w-60" label="Category" control={form.control} path="categoryId" options={categoryOptions} placeholder="Select Category"/>
                  <FormSelect className="w-60" label="Product" control={form.control} path="productId" options={productOptions} placeholder="Select Product"/>
                  <FormSelect className="w-60" label="Product" control={form.control} path="productId" options={sizeOptions} placeholder="Select Product"/>
                                       
                {/* <div className="flex gap-2">       
                  <Button type="submit" className=" bg-blue-800 hover:bg-blue-800 text-white">              
                    { isEditMode ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                    {isEditMode ? "Update Changes" : "Create Product"}
                  </Button> 
                </div> */}
                </div>
             
          {/* <ProductSearchForm /> */}
          
           {/* </form> */}
          </Form> 
      </div>
    );
  }
