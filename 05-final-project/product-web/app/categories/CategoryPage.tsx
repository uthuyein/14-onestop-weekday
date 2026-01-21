
"use client";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Edit2, Plus, Save} from "lucide-react";
import { createCategory,updateCategory} from "@/lib/server/category.server";
import { categorySchema ,CategoryForm} from "@/lib/type/category-types";
import { Form } from "@/components/ui/form";
import FormInput from "@/components/forms/form-input";
import FormSelect from "@/components/forms/form-select";
import { toast } from "sonner";

import {
  Table,
  TableBody,
  TableCell,  
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

export default function CategoryPage({ categories }: { categories: any[] }) {
  const form = useForm<CategoryForm>({
    resolver: zodResolver(categorySchema),
    defaultValues: {
      name: "",
      isActive: true,
      subCategory: null,
    },
  });
  const { reset, watch } = form;

  const handleEdit = (cat: CategoryForm) => {
  reset({
    id: cat.id,       
    name: cat.name,
    subCategory: cat.subCategory ?? null,
    isActive:cat.isActive
  });
};


const onSubmit = async (form: CategoryForm) => {
  try {
    if (form.id) {
      await updateCategory(form.id, form);
      toast.success("Category updated");
    } else {
      await createCategory(form);
      toast.success("Category created");
    }

    reset(); 
  } catch (e) {
    toast.error("Something went wrong");
  }
};
const isEditMode = !!watch("id");

  return (
    <div className="w-full space-y-5 p-5">
      <div className="flex space-x-1 items-center">
         { isEditMode ? <Save className="w-5 h-5 " /> : <Plus className="w-5 h-5" />}
        <h2 className="text-lg  ">
          {isEditMode ? "Update ":"Create"} Category Form</h2>
      </div>
      {/* --- FORM SECTION --- */}
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                <div className="flex items-end space-x-3">

                {/* Parent Category insert */}
                 <FormInput  control={form.control} path="name" label="Category Name" placeholder="Type category name !" className="" />
                
                {/* Parent Category Select */}
                  <FormSelect control={form.control} options={categories.map((cat) => ({ key: cat.id,value: cat.name}))} path="subCategory"  className="w-5"  placeholder="Please select one"/>

                {/* Active Status */}
                <div className="flex items-center gap-2">
                <FormInput  control={form.control} path="isActive" type="checkbox" className="mb-4 w-4 h-4" />Active
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
      
      {/* --- TABLE SECTION --- */}
      <div className="rounded-md border bg-white">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-[100px]">ID</TableHead>
              <TableHead>Category Name</TableHead>
              <TableHead>Parent</TableHead>
              <TableHead>Status</TableHead>
              <TableHead className="text-right">Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {categories.length === 0 ? (
              <TableRow>
                <TableCell colSpan={5} className="text-center py-8 text-muted-foreground">
                  No categories found.
                </TableCell>
              </TableRow>
            ) : (
              categories.map((cat) => (
                <TableRow key={cat.id}>
                  <TableCell className="font-mono text-xs">#{cat.id}</TableCell>
                  <TableCell className="font-medium">{cat.name}</TableCell>
                  <TableCell>{cat.subCategory?.name || "—"}</TableCell>
                  <TableCell>
                    <span className={`px-2 py-0.5 rounded-full text-[10px] font-bold uppercase ${
                      cat.isActive ? "bg-green-100 text-green-700" : "bg-slate-100 text-slate-600"
                    }`}>
                      {cat.isActive ? "Active" : "Inactive"}
                    </span>
                  </TableCell>
                  <TableCell className="text-right">
                    <button
                      onClick={() => handleEdit(cat)}
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



