import FormSelect from "@/components/forms/form-select";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { CustomerForm, MemberTypeList, SearchCustomer } from "@/lib/type/customer-types";
import { Form, FormInput, Search } from "lucide-react";
import { UseFormReturn } from "react-hook-form";

type PageType = {
  search: UseFormReturn<SearchCustomer>;    
  handleSearch: (data: SearchCustomer) => void;
 
};
export default function CustomerSearchPage({search,
  handleSearch}:PageType){
    return(
        <div className="mt-3">
        {/* <Form {...search}>
          <Card className="border
                w-full
                border-input
                rounded-xl
                focus-within:ring-2
                focus-within:ring-ring
                focus-within:ring-offset-0">
            <CardContent>
          <form onSubmit={search.handleSubmit(handleSearch)} className="flex gap-2">
            <FormSelect
                    control={search.control}
                    path="type"
                    options={MemberTypeList.map(m => ({ key: m, value: m }))}  
                    className="border-0
                                shadow-none
                                focus:ring-0
                                focus:ring-offset-0"            
                />
            <FormInput control={search.control} path="keyword" placeholder="Search..."  className="border-0
                  shadow-none
                  focus-visible:ring-0
                  focus-visible:ring-offset-0
                  px-0
                  flex-1"/>
              <Button className="bg-blue-500 hover:bg-blue-800">
              <Search /> 
            </Button>
          </form>
          </CardContent>
          </Card>
        </Form> */}
      </div>
    );
}