"use client"

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { ProductForm, productSchema } from "@/lib/type/product-types";
import { Save, Plus, Edit2 } from "lucide-react";
import { Form } from "@/components/ui/form";
import FormInput from "@/components/forms/form-input";
import { Table,TableHeader, TableRow, TableHead, TableBody, TableCell } from "@/components/ui/table";
import { createProduct, updateProduct } from "@/lib/server/product.server";
import { toast } from "sonner";
import FormSelect from "@/components/forms/form-select";

export default function ProductPage({ products,categories }: { products: any[] ,categories:any[]}) {
  const form = useForm<ProductForm>({
    resolver: zodResolver(productSchema),
    defaultValues: {
      name: "",
      isActive: true,
      categoryId:1,
      categoryName:""

    },
  });
const { reset, watch } = form;

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
  } catch (e) {
    toast.error("Something went wrong");
  }

};

const handleEdit = (prod: ProductForm) => {
  reset({
    id: prod.id,       
    name: prod.name,
    categoryId: prod.categoryId,
    isActive:prod.isActive
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
                
                  { <FormSelect control={form.control} path="categoryId" label="Category" options={categories.map((cat) => ({ key: cat.id,value: cat.name}))} />}      

                <div className="flex items-center gap-2">
                { <FormInput  control={form.control} path="isActive" type="checkbox" className="mb-4 w-4 h-4" />} active
               </div>
               

              <div className="flex gap-2">
                
                <button type="submit"
                    className="inline-flex items-center gap-2 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-800 disabled:opacity-50 transition-all text-sm font-medium">
              
                  { isEditMode ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                  {isEditMode ? "Update Changes" : "Create Category"}
                </button>
 
              </div>
              </div>
            </form>
        </Form> 

         <div className="rounded-md border bg-white">
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead className="w-[100px]">ID</TableHead>
                        <TableHead>Product Name</TableHead>
                        <TableHead>Category</TableHead>
                        <TableHead>Status</TableHead>
                        <TableHead className="text-right">Actions</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {products.length === 0 ? (
              <TableRow>
                <TableCell colSpan={5} className="text-center py-8 text-muted-foreground">
                  No categories found.
                </TableCell>
              </TableRow>
            ) : (
              products.map((prod) => (
                <TableRow key={prod.id}>
                  <TableCell className="font-mono text-xs">#{prod.id}</TableCell>
                  <TableCell className="font-medium">{prod.name}</TableCell>
                  <TableCell>{prod.categoryName || "—"}</TableCell>
                  <TableCell>
                    <span className={`px-2 py-0.5 rounded-full text-[10px] font-bold uppercase ${
                      prod.isActive ? "bg-green-100 text-green-700" : "bg-slate-100 text-slate-600"
                    }`}>
                      {prod.isActive ? "Active" : "Inactive"}
                    </span>
                  </TableCell>
                  <TableCell className="text-right">
                    <button
                      onClick={() => handleEdit(prod)}
                      className="inline-flex items-center justify-center w-8 h-8  hover:bg-slate-50 text-blue-600 transition-colors"
                    >
                      <Edit2 className="w-4 h-4" />
                    </button>
                  </TableCell>
                </TableRow>
              ))
            )}
                </TableBody>
          </Table>
         </div>
    </div>
  );
}