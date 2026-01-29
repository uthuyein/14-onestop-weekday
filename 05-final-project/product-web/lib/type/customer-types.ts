import {z} from "zod";

export const MemberType = {
  Silver: "Silver",
  Gold: "Gold",
  Diamond: "Diamond",
} as const;

export const customerSchema = z.object({
    id :z.number().optional(),
    name:z.string(),
    memberType:z.enum([MemberType.Silver,MemberType.Gold,MemberType.Diamond]),
    email:z.string(),
    primary:z.string(),
    secondary:z.string(),
    state:z.string(),
    township:z.string(),
    street:z.string(),
    isActive:z.boolean().optional()

})


export type CustomerForm = z.infer<typeof customerSchema>
export type MemberType = typeof MemberType[keyof typeof MemberType];

export const MemberTypeList = Object.values(MemberType);

export type SearchCustomer = {
    type?:string
    keyword?:string,
    
}

export type SelectCustomer = {
    id:number,
    name:string,
    memberType:MemberType
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

		
		