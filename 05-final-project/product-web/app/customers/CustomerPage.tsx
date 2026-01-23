
"use client";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Plus, Save} from "lucide-react";
import { Form } from "@/components/ui/form";
import FormInput from "@/components/forms/form-input";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { CustomerForm, CustomerListItem, customerSchema } from "@/lib/type/customer-types";
import { createCustomers, updateCustomer } from "@/lib/server/customer.server";
import { useRouter } from "next/navigation";
import CustomerTable from "@/components/forms/tables/table-customer";

export default function CustomerPage({ customers }: { customers: CustomerListItem[] }) {
    const router = useRouter();
    const form = useForm<CustomerForm>({
      resolver: zodResolver(customerSchema),
      defaultValues: {
        name: "",
        isActive: true,
        primary: "",
        secondary:"",
        email:"",
        state:"",
        township:"",
        street:""
      },
    });
    const { reset, watch } = form;

    const handleEdit = (cu:CustomerListItem,active? :boolean) => {
      
      reset({
        id: cu.id,       
        name: cu.name,
        primary:cu.contact.primaryPhone,
        secondary:cu.contact.secondaryPhone,
        isActive:active,
        state:cu.address.state,
        township:cu.address.township,
        street:cu.address.street
        });
    };

    const onSubmit = async (form: CustomerForm) => {
      try {
        if (form.id) {
          await updateCustomer(form.id, form);
          toast.success("Customer updated");
        } else {
          await createCustomers(form);
          toast.success("Customer created");
        }
        reset(); 
        router.refresh();

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
          {isEditMode ? "Update ":"Create"} Customer Form</h2>
      </div>
      <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
              <div className="flex items-end space-x-3">

                <FormInput  control={form.control} path="name" label="Customer Name" placeholder="Type customer name !" className="" />             
                {/* <FormSelect control={form.control} label="memberType" options={categories.map((cat) => ({ key: cat.id,value: cat.name}))} path="subCategoryId"  className="w-50"  placeholder="Please select one"/> */}
             
              <div className="flex gap-2">              
                <Button type="submit"  className="hover:bg-blue-800 bg-blue-500">            
                  { isEditMode ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                  {isEditMode ? "Update Changes" : "Create Customer"}
                </Button>
            </div>
            </div>
         
      
      {/* --- TABLE SECTION --- */}
      <CustomerTable customers={customers} onEdit={handleEdit} onDelete={handleEdit}/>
     </form>
    </Form>
    </div>
  );
}



