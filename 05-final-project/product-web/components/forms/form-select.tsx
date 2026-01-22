import { OptionItem } from "@/lib/type/types";
import { Control, FieldValues, Path } from "react-hook-form";
import { FormControl, FormField, FormItem, FormLabel } from "../ui/form";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "../ui/select";

type FormSelectProps<T extends FieldValues> = {
     control:Control<T>
      path: Path<T>
      label?: string
      className?: string
      placeholder?: string
      options:OptionItem[]
};

export default function FormSelect<T extends FieldValues>({
    control,
    path,
    label,
    className,      
    placeholder,
    options
}:FormSelectProps<T>) {
    return (
        <FormField control={control} name={path} render={({field}) => 
            <FormItem  className={className}>
                {label && <FormLabel>{label}</FormLabel>}
                 <Select value={field.value?.toString()}  onValueChange={(value) => {field.onChange(Number(value))}} >
                    <FormControl> 
                        <SelectTrigger className="w-full">
                            <SelectValue placeholder={placeholder || "Select an option"}/>
                        </SelectTrigger> 
                    </FormControl>  
                    <SelectContent>
                        {options.map((item,index )=> <SelectItem key={index} value={item.key.toString()}>{item.value}</SelectItem>)}
                    </SelectContent>
                </Select>
            </FormItem>
         } />

        
    );
}


