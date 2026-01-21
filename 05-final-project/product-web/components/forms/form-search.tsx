import { Control, FieldValues, Path } from "react-hook-form";
import { FormField, FormItem, FormLabel, FormControl } from "../ui/form";
import { Input } from "../ui/input";

type FormInputProps<T extends FieldValues> = {
    control:Control<T>
    path: Path<T>
    label?: string
    className?: string
    placeholder?: string
};

export default function FormInput<T extends FieldValues>({
    control,
    path,
    label,
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
                <FormControl>
                    <Input type="text" placeholder={placeholder} {...field} />
                </FormControl>
            </FormItem>}
        />
    );
}