"use client"
import { createProductPrice, findProductPrices, getProductPrices, updateProductPrice } from "@/lib/server/product.price.server";
import ProductPricePage from "@/app/productPrices/ProductPricePage";
import { getSizes } from "@/lib/server/size.server";
import { getCategories } from "@/lib/server/category.server";
import { getProducts } from "@/lib/server/product.server";
import { useRouter } from "next/navigation";
import { useForm } from "react-hook-form";
import { ProductPriceForm, productPriceSchema, SearchProductPriceForm, SelectProductPrice } from "@/lib/type/product-price-types";
import { zodResolver } from "@hookform/resolvers/zod";
import { toast } from "sonner";
import ProductPriceTable from "@/components/forms/tables/table-product-price";
import { useEffect, useState } from "react";

 const products = await getProducts();
const categories = await getCategories()
const sizes = await  getSizes();

export default  function Page () {
    
    const [productPrices, setProductPrices] =  useState<SelectProductPrice[]>([])
    const [loading, setLoading] = useState(false);

      const handleSearch = async (form: SearchProductPriceForm={}) => {
       setLoading(true);
        try {
             const result = await findProductPrices(form) 
             setProductPrices(result)
           } finally {
             setLoading(false);
           }      
      }
      
    const search = useForm<SearchProductPriceForm>({
        defaultValues:{
            keyword:""
        }
    })

    useEffect(() => {
        handleSearch();
      }, []);
    

    const router = useRouter();   
    const form = useForm<ProductPriceForm>({
        resolver:zodResolver(productPriceSchema),
        defaultValues:{ 
            categoryId:undefined,        
            productId:undefined, 
            sizeId:undefined,
            priceType:"",
            id:undefined, 
            price:0,
            isActive:true,
            createAt:new Date(),
            updateAt:new Date()         
        }
    })
    const { watch, reset} = form;
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

    const onDelete = async (id:number) => {}

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

    return (
        <div className="">
             <ProductPricePage form={form} searchForm={search} isEditMode={isEditMode} onSubmit={onSubmit} handleSearch={handleSearch} categories={categories}  products = {products} sizes={sizes}/>
             <ProductPriceTable prices={productPrices}  onEdit={handleEdit} onDelete={onDelete} />
        </div>
    )
}
