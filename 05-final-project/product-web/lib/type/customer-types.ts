import {z} from "zod";

export const customerSchema = z.object({
    id :z.number().optional(),
    name:z.string().nonempty("Please type customer name !"),
    memberType:z.string().optional(),
    email:z.string(),
    primary:z.string(),
    secondary:z.string(),
    state:z.string(),
    township:z.string(),
    street:z.string(),
    isActive:z.boolean()

})

export type CustomerForm = z.infer<typeof customerSchema>

export type CustomerListItem = {
    id:number,
    name:string,
    memberType:"Silver"|"Gold"|"Diamond"
    contact:{
        id:number,
        email:string,
        primaryPhone:string,
        secondaryPhone:string
    },
    address:{
        id:number,
        state:string,
        township:string,
        street:string
    }
}

		
		