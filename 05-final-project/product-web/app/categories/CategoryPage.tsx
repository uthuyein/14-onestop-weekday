
"use client";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Plus, Save} from "lucide-react";
import { createCategory,updateCategory} from "@/lib/server/category.server";
import { categorySchema ,CategoryForm, CategoryListItem} from "@/lib/type/category-types";
import { Form } from "@/components/ui/form";
import FormInput from "@/components/forms/form-input";
import FormSelect from "@/components/forms/form-select";
import { toast } from "sonner";
import CategoryTable from "@/components/forms/tables/table-category";
import { Button } from "@/components/ui/button";

export default function CategoryPage({ categories }: { categories: any[] }) {
  
  const form = useForm<CategoryForm>({
    resolver: zodResolver(categorySchema),
    defaultValues: {
      name: "",
      isActive: true,
      subCategoryId: undefined,
    },
  });
  const { reset, watch } = form;

  const handleEdit = (cat: CategoryListItem) => {
  reset({
    id: cat.id,       
    name: cat.name,
    subCategoryId: cat.subCategory.id ?? undefined,
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
      <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
              <div className="flex items-end space-x-3">

                <FormInput  control={form.control} path="name" label="Category Name" placeholder="Type category name !" className="" />
              
                <FormSelect control={form.control} label="Parent Category" options={categories.map((cat) => ({ key: cat.id,value: cat.name}))} path="subCategoryId"  className="w-50"  placeholder="Please select one"/>

                <div className="flex items-center gap-2">
                  <FormInput  control={form.control} path="isActive" type="checkbox" className="mb-4 w-4 h-4" />Active
                </div>

              <div className="flex gap-2">              
                <Button type="submit"  className="hover:bg-blue-800 bg-blue-500">            
                  { isEditMode ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                  {isEditMode ? "Update Changes" : "Create Category"}
                </Button>
            </div>
            </div>
          </form>
      </Form>
      
      {/* --- TABLE SECTION --- */}
      <CategoryTable categories={categories} onEdit={handleEdit} />
    
    </div>
  );
}



