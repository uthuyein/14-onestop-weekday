import {z} from "zod";

export const sizeSchema =  z.object({
    id:z.number().nonoptional("Select One size !"),
    name:z.string().nonempty()
});

export type SizeForm = z.infer<typeof sizeSchema>;

export type SizeListItem = {
    id:number
    name:string
}