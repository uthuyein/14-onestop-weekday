"use client"

import ProductPriceTable, { SelectProductPriceTable } from "@/components/forms/tables/table-product-price";
import { Select, SelectContent, SelectGroup, SelectLabel, SelectTrigger, SelectValue } from "@/components/ui/select";
import { ProductPriceForm, ProductPriceListItem, productPriceSchema, SearchProductPrice } from "@/lib/type/product-price-types";
import { SelectItem } from "@radix-ui/react-select";
import { useRouter } from "next/navigation";

export default function ProductPricePage({prices}:{prices : ProductPriceListItem[]}){
    
    const router = useRouter();
   
    const categories =  prices.map(p => p.category).map((cat) => ({ key: cat.id.toString(),value: cat.name}));
    const products =  prices.map(p => p.product).map((prod) => ({ key: prod.id.toString(),value: prod.name}));
  
    const onSubmit = async (form: ProductPriceListItem) => {
          
    
    };

    const handleEdit = (prod: ProductPriceListItem,active?:boolean) => {
      
     
    };

    return(
        <div className="w-full space-y-5 p-5">
               <Select>
                <SelectTrigger className="">
                    <SelectValue placeholder="Select a Category" />
                </SelectTrigger>
                <SelectContent>
                    <SelectGroup>
                    <SelectLabel>Category</SelectLabel>
                       {categories.map((item,index )=> <SelectItem key={index} value={item.key.toString()}>{item.value}</SelectItem>)}
                    </SelectGroup>
                </SelectContent>
                </Select>
               
            <Select>
                <SelectTrigger className="">
                    <SelectValue placeholder="Select a Product" />
                </SelectTrigger>
                <SelectContent>
                    <SelectGroup>
                    <SelectLabel>Product</SelectLabel>
                       {products.map((item,index )=> <SelectItem key={index} value={item.key.toString()}>{item.value}</SelectItem>)}
                    </SelectGroup>
                </SelectContent>
                </Select>
               
        </ div>
    );
}