import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"
import { OptionItem } from "./type/types";


export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function queryString(form:{[key:string]: unknown}){
    const params = new URLSearchParams();
    
    Object.keys(form).forEach(key=>{
        if(form[key] !== undefined && form[key] !== null){
            params.append(key, String(form[key]));
        }
    });
    return params.toString();
}

export const GET_CONFIG:RequestInit = {
  method: "Get",
  cache: "no-store",
    
}

export const POST_CONFIG:RequestInit = {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
}


export const PUT_CONFIG:RequestInit = {
  method: "PUT",
  headers: {
    "Content-Type": "application/json",
  },
}

export const STATUS_OPTIONS:OptionItem[]=[
  {key : "false" ,value : "Active"},
  {key : "true",value: "Deleted"}
]


 