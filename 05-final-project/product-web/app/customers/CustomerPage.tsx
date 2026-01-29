
"use client";

import {  UseFormReturn } from "react-hook-form";
import { Plus, Save, Search} from "lucide-react";
import { Form } from "@/components/ui/form";
import FormInput from "@/components/forms/form-input";
import { Button } from "@/components/ui/button";
import { CustomerForm, SearchCustomer, MemberTypeList} from "@/lib/type/customer-types";
import FormSelect from "@/components/forms/form-select";
import { Card, CardContent } from "@/components/ui/card";

type PageType = {
  form: UseFormReturn<CustomerForm>;
  isEdit?:boolean;
  search: UseFormReturn<SearchCustomer>;
  handleSearch: (data: SearchCustomer) => void;
  onSubmit: (data: CustomerForm) => void;
};

export default function CustomerEdit({
  form,
  isEdit,
  search,
  handleSearch,
  onSubmit,
}: PageType) {

  
  return (
    <div className="flex w-full gap-4 space-y-2 items-baseline-last">
      <div className="w-full ">
        <div className="flex gap-2 items-center py-3">
          {isEdit ? <Save className="w-5 h-5" /> : <Plus className="w-5 h-5" />}
          <h2 className="text-lg">
            {isEdit ? "Update" : "Create"} Customer Form
          </h2>
        </div>

            <Form {...form}>
              <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                <div className="flex gap-2">
                  <FormInput control={form.control} path="name" label="Customer Name" />
                  <FormSelect
                    control={form.control}
                    path="memberType"
                    label="Member Type"
                    options={MemberTypeList.map(m => ({ key: m, value: m }))}
                    className="w-sm"
                  />
                </div>

                <div className="flex gap-2">
                  <FormInput control={form.control} path="email" label="Email" />
                  <FormInput control={form.control} path="primary" label="Primary Phone" />
                  <FormInput control={form.control} path="secondary" label="Secondary Phone" />
                </div>

                <div className="flex gap-2">
                  <FormInput control={form.control} path="state" label="State" />
                  <FormInput control={form.control} path="township" label="Township" />
                  <FormInput control={form.control} path="street" type="textarea" label="Address" />
                  <Button type="submit" className="bg-blue-500 hover:bg-blue-800 mt-6 pt-3 ">
                    {isEdit ? "Update Changes" : "Create Customer"}
                 </Button>
                </div>                
              </form>
            </Form>        
      </div>

      <div className="flex mt-9">
        <Form {...search}>
          <Card className="
                w-xl
                h-2
                rounded-xl
               ">
            <CardContent>
          <form onSubmit={search.handleSubmit(handleSearch)} className="flex gap-2">
            <FormSelect
                    control={search.control}
                    path="type"
                    options={MemberTypeList.map(m => ({ key: m, value: m }))}  
                    className=""            
                />
            <FormInput control={search.control} path="keyword" placeholder="Search..."  className="border-0
                  shadow-none
                  px-0
                  flex-1"/>
              <Button className="bg-blue-500 hover:bg-blue-800">
              <Search /> 
            </Button>
          </form>
          </CardContent>
          </Card>
        </Form>
      </div>
    </div>
  );
}




