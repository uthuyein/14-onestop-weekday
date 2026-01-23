
import { ChartColumnStacked, CircleDollarSign, FileText, Handbag, Home, ShoppingBasket, UserLock, UserRoundPen, UserStar } from "lucide-react";
import Link from "next/link";

export default function MenuBar() {
    return (
        <nav className="bg-white shadow-md p-4">
            <div className="flex justify-between px-4">
               
                <Link href="/" className="flex items-start gap-2 px-2 py-2">
                   <Home/> Product Store
                </Link>
               <div className="flex">
                    <div className="flex gap-2">
                         <Link href="/categories" className="flex items-center gap-2 px-2 py-2">
                         <ChartColumnStacked/> Category 
                         </Link>
                         <Link href="/products" className="flex items-center gap-2 px-2 py-2">
                              <FileText/> Product 
                         </Link> 
                         <Link href="/productPrices" className="flex items-center gap-2 px-2 py-2">
                              <CircleDollarSign/> Product Price
                         </Link> 
                         <Link href="/customers" className="flex items-center gap-2 px-2 py-2">
                              <UserStar/> Customer 
                         </Link> 
                         <Link href="/suppliers" className="flex items-center gap-2 px-2 py-2">
                              <UserRoundPen/> Supplier 
                         </Link> 
                         <Link href="/purchases" className="flex items-center gap-2 px-2 py-2">
                              <Handbag/> Purchase
                         </Link> 
                         <Link href="/sales" className="flex items-center gap-2 px-2 py-2">
                              <ShoppingBasket/> Sales
                         </Link>              
                    </div> 
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