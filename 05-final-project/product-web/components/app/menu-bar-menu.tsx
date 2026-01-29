import { IconType, LinkItem } from "@/lib/type/types";
import { MenubarCheckboxItem, MenubarContent, MenubarMenu, MenubarTrigger } from "../ui/menubar";
import IconComponent from "./icon-component";
import Link from "next/link";

export type menuType = {
    menu :string,
    pIcon:IconType
    items:LinkItem[]

}

export function MenubarCheckbox({menu,pIcon,items} : menuType){
  return (
    
      <MenubarMenu >
        <MenubarTrigger>
            <IconComponent icon={pIcon} />&nbsp; {menu}
        </MenubarTrigger>
        <MenubarContent> 
            {items.map((item,index )=> 
            <MenubarCheckboxItem key={index}  >
                    <Link href={item.link}  className="flex items-center gap-2 px-2 py-2 ">
                        <IconComponent icon={item.icon || pIcon} /> {item.name}
                    </Link>
            </MenubarCheckboxItem>)}      
        
        </MenubarContent>
      </MenubarMenu>
   
  )
}