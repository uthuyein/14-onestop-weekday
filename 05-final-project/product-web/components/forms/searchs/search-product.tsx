import { Form } from "@/components/ui/form";
import { SearchProductForm } from "@/lib/type/product-types";
import { queryString } from "@/lib/utils";
import { useRouter, useSearchParams } from "next/navigation";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import FormInput from "../form-input";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import { Plus } from "lucide-react";

export default function ProductSearchForm(){
    const searchParam = useSearchParams()
    const router =useRouter()

    const form = useForm<SearchProductForm>({
        defaultValues:{
            isActive:true,
            keyword:""
        }
    })

    useEffect(() =>{
        const active = searchParam.get("isActive")
        const keyword = searchParam.get("keyword")

        form.reset({
            isActive:true || false,
            keyword:keyword || ""
        })
    },[form,searchParam])

    function search(form:SearchProductForm){
        if(form.isActive){
            delete form.isActive
        }
        router.push(`/member/product?${queryString(form)}`)
    }
return(
    <Form {...form}>
        <form onSubmit={form.handleSubmit(search)} className="flex items-end">
            <FormInput  control={form.control} path="isActive" type="checkbox"  className="mb-4 w-4 h-4" />
            <FormInput control={form.control} path="keyword" label="Keyword" placeholder="Search Keyword" />
            <Button type="button" variant={'destructive'} asChild>
                <Link href={'/products'}>
                    <Plus/>Create Course
                </Link>
            </Button>
        </form>
    </Form>
)
    
}