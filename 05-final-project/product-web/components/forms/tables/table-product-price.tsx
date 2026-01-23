"use client";

import { Edit2, Trash2 } from "lucide-react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import {ProductPriceListItem } from "@/lib/type/product-price-type";
import { Button } from "@/components/ui/button";



type PriceTableProps = {
  prices:ProductPriceListItem[];
  onEdit: (prod:ProductPriceListItem) => void;
  onDelete:(prod:ProductPriceListItem,active:boolean) => void
};

export default function ProductPriceTable({prices,onEdit,onDelete}: PriceTableProps) {
  
  return (
    <div className="border bg-white ">
      <Table  className="table-fixed w-full ">
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Category</TableHead>
            <TableHead>Product</TableHead>
            <TableHead>Size</TableHead>
            <TableHead>Price Type</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Created At</TableHead>
            <TableHead className="text-center">Updated At</TableHead>
          </TableRow>
        </TableHeader>

        <TableBody>
          {prices.length === 0 ? (
            <TableRow>
              <TableCell
                colSpan={5}
                className="text-center py-8 text-muted-foreground"
              >
                No categories found.
              </TableCell>
            </TableRow>
          ) : (
            prices.map((prod) => (
              <TableRow key={prod.id}>
                <TableCell className="font-mono text-xs">
                  #{prod.id}
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
                <TableCell>
                  {prod.createAt }
                </TableCell>
                <TableCell className="text-center">
                  {prod.updateAt || "-"}
                </TableCell>
                 <TableCell className="text-right">
                    <div className="flex justify-end gap-2">
                      <Button
                        onClick={() => onEdit(prod)}
                        className="hover:bg-blue-50 text-blue-600 bg-bg-light"
                      >
                        <Edit2 className="w-4 h-4" />
                      </Button>
                     <Button
                        onClick={() => onDelete(prod,false)}
                        className=" hover:bg-red-50 rounded text-red-600 bg-bg-light"
                      >
                        <Trash2 className="w-4 h-4" />
                      </Button>
                    </div>
                  </TableCell>
              </TableRow>
            ))
          )}
        </TableBody>
      </Table>
    </div>
  );
}
