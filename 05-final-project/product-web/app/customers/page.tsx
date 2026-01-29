"use client";

import CustomerPage from "./CustomerPage";
import {  CustomerForm, customerSchema, MemberType, SearchCustomer, SelectCustomer,  } from "@/lib/type/customer-types";
import CustomerTable from "@/components/forms/tables/table-customer";
import { useEffect, useState } from "react";
import { createCustomers, deactivateCustomer, findCustomers, updateCustomer } from "@/lib/server/customer.server";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { toast } from "sonner";
import { useRouter } from "next/navigation";

export default function Page() {
  const [selectCustomer, setSelectCustomers] = useState<SelectCustomer[]>([]);
  const [loading, setLoading] = useState(false);

  const form = useForm<CustomerForm>({
    resolver: zodResolver(customerSchema),
    defaultValues: {
      id:undefined,
      name: "",
      memberType:MemberType.Silver,
      isActive: true,
      primary: "",
      secondary: "",
      email: "",
      state: "",
      township: "",
      street: "",
    },
  });

  const search = useForm<SearchCustomer>({
    defaultValues: { 
      type:"",
      keyword: "" 
      
    },

  });

  const router = useRouter();
  const { reset, watch } = form;
  const isEditMode = !!form.watch("id");

  const onSubmit = async (data: CustomerForm) => {
    try {
       
      if (data.id) {
        await updateCustomer(data.id, data);
        toast.success("Customer updated");
      } else {
        await createCustomers(data);
        toast.success("Customer created");
      }
      reset();
      router.refresh();
      handleSearch();
    } catch {
      toast.error("Something went wrong");
    }
  };
const handleDelete = async (id: number) => {
  try {
    console.log("Delete :::  "+id)
    await deactivateCustomer(id);
    toast.success("Customer deactivated"); 
    handleSearch();   
  } catch (e) {
    toast.error("Failed to deactivate");
  }
};
  const handleSearch = async (data: SearchCustomer = {}) => {
    setLoading(true);
    try {
      const result = await findCustomers(data);
      setSelectCustomers(result);
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = (cu: SelectCustomer) => {
    reset({
      id: cu.id,
      name: cu.name,
      memberType: cu.memberType,
      email: cu.contact.email,
      primary: cu.contact.primaryPhone,
      secondary: cu.contact.secondaryPhone,
      state: cu.address.state,
      township: cu.address.township,
      street: cu.address.street,
      isActive: true,
    });
     reset();
  };

  useEffect(() => {
    handleSearch();
  }, []);

  return (
    <div className="space-y-4">
      <CustomerPage
        form={form}
        isEdit={isEditMode}
        search={search}
        handleSearch={handleSearch}
        onSubmit={onSubmit}
      />

      <CustomerTable
        customers={selectCustomer}
        loading={loading}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />
    </div>
  );
}


  
 

