"use client";

import { DiamondPlus, Edit2, Trash2 } from "lucide-react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import {SelectProductPrice } from "@/lib/type/product-price-types";
import { Button } from "@/components/ui/button";



type PriceTableProps = {
  prices:SelectProductPrice[];
  onEdit: (prod:SelectProductPrice) => void;
  onDelete:(prod: SelectProductPrice,active:boolean ) => void
};


export default function PurchaseDetailTable({prices,onEdit,onDelete}: PriceTableProps) {
  return (
    <div className="border bg-white ">
      <Table  className="table-fixed w-full ">
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">No.</TableHead>
            <TableHead>Product</TableHead>
            <TableHead>Size</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Qty</TableHead>
            <TableHead>total</TableHead>
          </TableRow>
        </TableHeader>

        <TableBody>
          {prices.length === 0 ? (
            <TableRow>
              <TableCell
                colSpan={5}
                className="text-center py-8 text-muted-foreground"
              >
                No Product Price found.
              </TableCell>
            </TableRow>
          ) : (
            prices.map((prod,index) => (
              <TableRow key={prod.id}>
                <TableCell className="font-mono text-xs">
                  #{index+1}
                </TableCell>
                <TableCell className="font-medium">
                  {prod.category.name}
                </TableCell>
                <TableCell>
                  {prod.product.name }
                </TableCell>
                <TableCell>
                  {prod.size.name }
                </TableCell>
                 <TableCell>
                  {prod.priceType }
                </TableCell>
                <TableCell>
                  {prod.price }
                </TableCell>
                 <TableCell className="text-right">                 
                  </TableCell>
              </TableRow>
            ))
          )}
        </TableBody>
      </Table>
    </div>
  );
}

