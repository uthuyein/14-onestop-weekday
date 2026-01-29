"use client"

import { FormDatePicker } from "@/components/forms/form-date";
import FormInput from "@/components/forms/form-input";
import FormSelect from "@/components/forms/form-select";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Form, FormField } from "@/components/ui/form";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { CategoryListItem } from "@/lib/type/category-types";
import {  ProductPriceForm, SearchProductPriceForm } from "@/lib/type/product-price-types";
import { ProductListItem } from "@/lib/type/product-types";
import { SizeListItem } from "@/lib/type/size-type";
import { Plus, Save, Search } from "lucide-react";
import { UseFormReturn } from "react-hook-form";

export type ProductPriceProps ={
    form:UseFormReturn<ProductPriceForm>,
    searchForm:UseFormReturn<SearchProductPriceForm>,
    isEditMode:boolean,
    onSubmit: (form: ProductPriceForm) => void,
    handleSearch:(form:SearchProductPriceForm) => void,
    categories:CategoryListItem[],
    products :ProductListItem[],
    sizes : SizeListItem[]
}

export default   function ProductPricePage({form,searchForm,isEditMode,onSubmit,handleSearch,categories,products,sizes}:ProductPriceProps){
     
    return(
        <div className="flex items-baseline-last">
        <div className="w-full space-y-5 p-5">
            <div className="flex space-x-1 items-center">
                { isEditMode ? <Save className="w-5 h-5 " /> : <Plus className="w-5 h-5" />}
                <h2 className="text-lg  ">
                {isEditMode ? "Update ":"Create"} Product Price Form</h2>
            </div>
            <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                <div className="flex space-x-2 mb-3">
                    <FormSelect className="w-60" label="Category" control={form.control} path="categoryId" options={ categories.map((cat) => ({ key: cat.id.toString(),value: cat.name}))} placeholder="Select Category"/>
                    <FormSelect className="w-60" label="Product" control={form.control} path="productId" options={products.map((prod) => ({ key: prod.id.toString(),value: prod.name}))} placeholder="Select Product"/>
                    
                     <div className=" w-60 h-9 mt-5 rounded-md items-center p-3">
                        <FormField control={form.control} name="priceType" render={({ field }) => (
                            <RadioGroup className="flex " value={field.value} onValueChange={field.onChange}>
                                <div className="flex items-center gap-2">
                                    <RadioGroupItem value="Sale" id="Sale" />
                                    <Label htmlFor="Sales">Sale</Label>
                                </div>

                                <div className="flex items-center gap-2">
                                    <RadioGroupItem value="Purchase" id="Purchase" />
                                    <Label htmlFor="Purchase">Purchase</Label>
                                </div>
                            </RadioGroup>
                            )}/>
                    </div>
                </div>
                <div className="flex space-x-2 mb-3">
                    <FormSelect className="w-60" label="Size" control={form.control} path="sizeId" options={sizes.map((size) => ({ key: size.id.toString(),value: size.name}))} placeholder="Select Size"/>
                    {/* <FormDatePicker control={form.control} name="createAt" label="Created At" className="flex flex-col"/> */}
                    <FormInput className="w-60" control={form.control} path="price" label="Price" placeholder="Enter price "/>                               
                   
                    <div className="items-baseline gap-2 mt-5 px-2">              
                        <Button type="submit"  className="hover:bg-blue-800 bg-blue-500">            
                        { isEditMode ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                        {isEditMode ? "Update Changes" : "Create Product Price"}
                        </Button>
                    </div>
               </div>
               </form>
            </Form>            
        </ div>
         <div className=" flex">
        <Form {...searchForm} >
          <Card className=" w-xl h-2 rounded-xl ">
            <CardContent>
          <form onSubmit={searchForm.handleSubmit(handleSearch)} className="flex gap-2">
            <FormInput control={searchForm.control} path="keyword" placeholder="Search..."  className="border-0
                  shadow-none
                  
                  flex-1"/>
              <Button className="bg-blue-500 hover:bg-blue-800">
              <Search /> 
            </Button>
          </form>
          </CardContent>
          </Card>
        </Form>
      </div>
      </div>
    );
}