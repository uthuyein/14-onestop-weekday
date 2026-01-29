import { Label } from "@radix-ui/react-label";
import { RadioGroup, RadioGroupItem } from "@radix-ui/react-radio-group";
import {Control, FieldValues, Path } from "react-hook-form";
import { FormField } from "../ui/form";

type FormSelectProps<T extends FieldValues> = {
        control:Control<T>
        path: Path<T>
        className?: string

      
};

export default function FormSalePurchaseRadio<T extends FieldValues>({
        control,
        path,
        className,
    }:FormSelectProps<T>) {
        return (
               <FormField control={control} name={path} render={({ field }) => (
                <RadioGroup
                        className="flex mt-5"
                        value={field.value}
                        onValueChange={field.onChange}
                        >
                        <div className={className}>
                            <RadioGroupItem value="Sales" id="Sales" />
                            <Label htmlFor="Sales">Sales</Label>
                        </div>

                        <div className={className}>
                            <RadioGroupItem value="Purchase" id="Purchase" />
                            <Label htmlFor="Purchase">Purchase</Label>
                        </div>
                </RadioGroup> 
                        )}/>

    )}
