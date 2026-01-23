"use client"

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { ProductForm, ProductListItem, productSchema } from "@/lib/type/product-types";
import { Save, Plus } from "lucide-react";
import { Form } from "@/components/ui/form";
import FormInput from "@/components/forms/form-input";
import { createProduct, updateProduct } from "@/lib/server/product.server";
import { toast } from "sonner";
import FormSelect from "@/components/forms/form-select";
import ProductTable from "@/components/forms/tables/table-product";
import { Button } from "@/components/ui/button";
import { CategoryListItem } from "@/lib/type/category-types";
import { useRouter } from "next/navigation";

export default function ProductPage({ products,categories }: { products: ProductListItem[] ,categories:CategoryListItem[]}) {
  const router = useRouter();

    const form = useForm<ProductForm>({
      resolver: zodResolver(productSchema),
      defaultValues: {
        name: "",
        isActive: true,
        categoryId:undefined,
        
      },
    });
    const { reset, watch } = form;

    const categoryId = watch("categoryId");
    const name = watch("name");
    
    const filteredProducts = products.filter((p) => {
        if (categoryId && p.category.id !== categoryId) return false;
        if (name && p.name !== name) return false;
        return true;
        });

    const handleDeactivate = async (id: number) => {
   
    };
    const onSubmit = async (form: ProductForm) => {

     try {
        if (form.id) {
            await updateProduct(form.id, form);
            toast.success("Category updated");
        } else {
            await createProduct(form);
            toast.success("Category created");
            }

            reset(); 
            router.refresh()
        } catch (e) {
            toast.error("Something went wrong");
        }
    };

    const handleEdit = (prod: ProductListItem) => {
      reset({
        id: prod.id,      
        name: prod.name,
        isActive:prod.isActive,
        categoryId:prod.category.id  
      });
    };
    const isEditMode = !!watch("id");

    return (
      <div className="w-full space-y-5 p-5">
        <div className="flex space-x-1 items-center">
          { isEditMode ? <Save className="w-5 h-5 " /> : <Plus className="w-5 h-5" />}
          <h2 className="text-lg  ">
            {isEditMode ? "Update ":"Create"} Product Form</h2>
        </div>
        {/* --- FORM SECTION --- */}
          <Form {...form}>
              <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                  <div className="flex items-end space-x-3">
            
                    { <FormInput  control={form.control} path="name" label="Product Name" placeholder="Type product name !" className="" /> }              
                    { <FormSelect control={form.control} path="categoryId" label="Category" options={categories.map((cat) => ({ key: cat.id,value: cat.name}))}  className="w-50" placeholder="Select Category"/>}      
    
                <div className="flex gap-2">
                  
                  <Button type="submit"
                      className=" bg-blue-500 hover:bg-blue-800">              
                    { isEditMode ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                    {isEditMode ? "Update Changes" : "Create Product"}
                  </Button>
  
                </div>
                </div>
              </form>
          </Form> 
          {/* <ProductSearchForm /> */}
          <ProductTable products={filteredProducts} onEdit={handleEdit} handleDeactivate={handleDeactivate} />
          
      </div>
    );
  }