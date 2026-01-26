"use client"

import { FormDatePicker } from "@/components/forms/form-date";
import FormInput from "@/components/forms/form-input";
import FormSelect from "@/components/forms/form-select";
import ProductPriceTable from "@/components/forms/tables/table-product-price";
import { Button } from "@/components/ui/button";
import { Form, FormField } from "@/components/ui/form";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { createProductPrice, updateProductPrice } from "@/lib/server/product.price.server";
import { ProductPriceForm, ProductPriceListItem, productPriceSchema } from "@/lib/type/product-price-types";
import { SizeListItem } from "@/lib/type/size-type";
import { zodResolver } from "@hookform/resolvers/zod";
import { Plus, Save } from "lucide-react";
import { useRouter } from "next/navigation";
import { useForm } from "react-hook-form";
import { toast } from "sonner";

export default function ProductPricePage({prices,sizes}:{prices : ProductPriceListItem[],sizes : SizeListItem[]}){
    
    const router = useRouter();
    
    const form = useForm<ProductPriceForm>({
        resolver:zodResolver(productPriceSchema),
        defaultValues:{           
            categoryId:undefined,
            productId:undefined,
            sizeId:undefined,
            priceType:"Sale",
            price:undefined,
            createAt:new Date(),
            udpateAt:new Date()
        }
    })
    const {watch,reset} = form
    const categoryId = watch("categoryId");
    const productId = watch("productId");
    const sizeId = watch("sizeId");
    const priceType = watch("priceType");
    const createAt = watch("createAt")

    const filteredPrices = prices.filter((p) => {
        if (categoryId && p.category.id !== categoryId) return false;
        if (productId && p.product.id !== productId) return false;
        if (sizeId && p.size.id !== sizeId) return false;
        if (priceType && p.priceType !== priceType) return false;
        // if(createAt && p.createAt !== createAt) return false
        return true;
        });
   
    const categories = Array.from(new Map( prices.map(p => [p.category.id, p.category])).values())
                        .map(cat => ({ key: cat.id.toString(),value: cat.name,}));

    const products = Array.from(new Map( prices.map(p => [p.product.id, p.product])).values())
                        .map(p => ({ key: p.id.toString(),value: p.name,}));

    const isEditMode = !!watch("id"); 

    const onSubmit = async (form: ProductPriceForm) => {
         console.log("create ::::"+form.categoryId)
     try {
        if (form.id) {
            await updateProductPrice(form.id, form);        
            toast.success("Product Price updated");
        } else {
            await createProductPrice(form);
            
            toast.success("Product Price created");
            }

            reset(); 
            router.refresh()
        } catch (e) {
            toast.error("Something went wrong");
        }
    };
    
    const handleEdit = (prod: ProductPriceListItem,active?:boolean) => {
        reset({
            id:prod.id,
            categoryId:prod.category.id,
            productId: prod.product.id,
            sizeId: prod.size.id,
            priceType: prod.priceType,
            price: prod.price,
            isActive:active,
            
        });
    };
    
    return(
        <div className="w-full space-y-5 p-5">
            <div className="flex space-x-1 items-center">
                { isEditMode ? <Save className="w-5 h-5 " /> : <Plus className="w-5 h-5" />}
                <h2 className="text-lg  ">
                {isEditMode ? "Update ":"Create"} Product Price Form</h2>
            </div>
            <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                <div className="flex space-x-2 mb-3">
                    <FormSelect className="w-60" label="Category" control={form.control} path="categoryId" options={categories} placeholder="Select Category"/>
                    <FormSelect className="w-60" label="Product" control={form.control} path="productId" options={products} placeholder="Select Product"/>
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
                    <FormDatePicker control={form.control} name="createAt" label="Created At" className="flex flex-col"/>
                    <FormInput className="w-60 mt-5.5" control={form.control} path="price"  placeholder="Enter price "/>                               
                   
                    <div className="items-baseline gap-2 mt-5 px-2">              
                        <Button type="submit"  className="hover:bg-blue-800 bg-blue-500">            
                        { isEditMode ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                        {isEditMode ? "Update Changes" : "Create Product Price"}
                        </Button>
                    </div>
               </div>
               <ProductPriceTable prices={filteredPrices}  onEdit={handleEdit} onDelete={handleEdit}/>
               </form>
            </Form>
            
        </ div>
    );
}