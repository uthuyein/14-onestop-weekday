
import { Home, icons, UserLock,} from "lucide-react";
import { Menubar,MenubarCheckboxItem,MenubarContent,MenubarMenu, MenubarTrigger,} from "@/components/ui/menubar"
import Link from "next/link";
import { MenubarCheckbox } from "./menu-bar-menu";

export default function MenuBar() {
    return (
        <nav className="bg-white shadow-md p-4">
            <div className="flex justify-between px-4">
               
                <Link href="/" className="flex items-start gap-2 px-2 py-2">
                   <Home/> Product Store
                </Link>
               <div className="flex">
                         <Menubar className="border-0 shadow-none" >
                         < MenubarCheckbox  items={[
                              { link: "/categories", name: "Category Form",icon:"User"},
                               { link: "/products", name: "Product Form"}
                              ]} menu={"Product "} pIcon="FileText"/>                      
                          < MenubarCheckbox  items={[
                              { link: "/productPrices", name: "Product Price Form",},
                              ]} menu={"Product Price"} pIcon="CircleDollarSign"/>
                           < MenubarCheckbox  items={[
                              { link: "/customers", name: "Customer List"}
                              ]} menu={"Customer"} pIcon="UserStar"/>
                         < MenubarCheckbox  items={[
                              { link: "/purchases", name: "Purchase Form" },
                              { link: "/purchases/search", name: "Purchase List"}
                              ]} menu={"Purchase"} pIcon="Handbag"/>
                         < MenubarCheckbox  items={[
                              { link: "/sales", name: "Purchase Form" },
                              { link: "/sales/search", name: "Sale List"}
                              ]} menu={"Sale"} pIcon="ShoppingBasket"/>             
                     </Menubar>
                    <div className="px-3 ms-5">
                         <Link href="/sales" className="flex text-blue-400 items-center gap-2 px-2 py-2">
                              <UserLock/>
                         </Link> 
                    </div>
                    
               </div>            
            </div>
        </nav>
    );
}



