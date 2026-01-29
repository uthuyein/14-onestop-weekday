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
import { CategoryListItem } from "@/lib/type/category-types";
import { ProductPriceForm, SelectProductPrice, productPriceSchema } from "@/lib/type/product-price-types";
import { ProductListItem } from "@/lib/type/product-types";
import { SizeListItem } from "@/lib/type/size-type";
import { zodResolver } from "@hookform/resolvers/zod";
import { Plus, Save } from "lucide-react";
import { useRouter } from "next/navigation";
import { useForm } from "react-hook-form";
import { toast } from "sonner";

export default function ProductPricePage({categories,products, prices,sizes}:{categories:CategoryListItem[],products :ProductListItem[],prices : SelectProductPrice[],sizes : SizeListItem[]}){
    
    const router = useRouter();
    
    const form = useForm<ProductPriceForm>({
        resolver:zodResolver(productPriceSchema),
        defaultValues:{           
            sizeId:undefined,
            priceType:undefined,
            price:0,
            isActive:true,
            createAt:new Date()
          
        }
    })
    const { watch, reset ,formState: { errors }} = form;
    console.log("Validation Errors:", errors);

    const watchedFields = watch(["productId", "sizeId", "priceType", "createAt"]);
    const [prodId, szId, pType] = watchedFields;

    const filteredPrices = prices.filter((p) => {
        if (prodId && p.product.id !== prodId) return false;
        if (szId && p.size.id !== szId) return false;
        if (pType && p.priceType !== pType) return false;
            
        return true;
    });

    const isEditMode = !!watch("id"); 

    const onSubmit = async (data: ProductPriceForm) => {
        try {
            if (data.id) {
                await updateProductPrice(data.id, data);        
                toast.success("Price updated successfully");
            } else {
                await createProductPrice(data);
                toast.success("Price created successfully");
            }
            reset(); 
            router.refresh();
        } catch (e) {
            toast.error("An error occurred saving the price");
        }
    };

    const handleEdit = (prod: SelectProductPrice,active?:boolean) => {
        reset({
            id:prod.id,
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
                    <FormDatePicker control={form.control} name="createAt" label="Created At" className="flex flex-col"/>
                    <FormInput className="w-60" control={form.control} path="price" label="Price" placeholder="Enter price "/>                               
                   
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