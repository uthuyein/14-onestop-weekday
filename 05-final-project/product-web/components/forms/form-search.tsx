import { Button } from "@/components/ui/button"
import { ButtonGroup } from "@/components/ui/button-group"
import { Field, FieldLabel } from "@/components/ui/field"
import { Search } from "lucide-react"
import { HTMLInputTypeAttribute } from "react";
import { Control, FieldValues, Path } from "react-hook-form";
import { FormControl, FormField, FormItem, FormLabel } from "../ui/form";
import { Input } from "../ui/input";



type FormInputProps<T extends FieldValues> = {
    control:Control<T>
    path: Path<T>
    label?: string
    type?: HTMLInputTypeAttribute
    className?: string
    placeholder?: string
};

export default function FormSearch<T extends FieldValues>({
    control,
    path,
    label,
    type,
    className,      
    placeholder     
}: FormInputProps<T>) {

    return (
        <FormField 
            control={control} 
            name={path} 
            render={({field}) => 
            <FormItem className={className}>
                {label && <FormLabel>{label}</FormLabel>}
                < FormControl>
                    <ButtonGroup>               
                        <Input type={type} placeholder={placeholder} {...field} />
                        <Button variant="outline"><Search/></Button>
                     </ButtonGroup>
                </FormControl>  
            </FormItem>}
        />
    );
}

