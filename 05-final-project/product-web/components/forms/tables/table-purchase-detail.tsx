"use client";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import {SelectProductPrice } from "@/lib/type/product-price-types";



type PurchaseDetailTableProps = {
  prices: (SelectProductPrice & { qty: number })[];
  onQtyChange?: (id: number, qty: number) => void;
};

export default function PurchaseDetailTable({ prices, onQtyChange }: PurchaseDetailTableProps) {
  return (
    <div className="border bg-white w-full">
      <Table className="">
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">No.</TableHead>
            <TableHead>Category</TableHead>
            <TableHead>Product</TableHead>
            <TableHead>Size</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Qty</TableHead>
            <TableHead>Total</TableHead>
          </TableRow>
        </TableHeader>

        <TableBody>
          {prices.length === 0 ? (
            <TableRow>
              <TableCell colSpan={7} className="text-center py-8 text-muted-foreground">
                No Product Price found.
              </TableCell>
            </TableRow>
          ) : (
            prices.map((prod, index) => (
              <TableRow key={prod.id}>
                <TableCell className="font-mono text-xs">#{index + 1}</TableCell>
                <TableCell>{prod.category.name}</TableCell>
                <TableCell>{prod.product.name}</TableCell>
                <TableCell>{prod.size.name}</TableCell>
                <TableCell>{prod.price}</TableCell>
                <TableCell>
                  <input
                    type="number"
                    className="w-16 border px-1 rounded text-right"
                    value={prod.qty}
                    min={0}
                    onChange={(e) => onQtyChange?.(prod.id, parseInt(e.target.value))}
                  />
                </TableCell>
                <TableCell className="text-right">{(prod.price * prod.qty).toFixed(2)}</TableCell>
              </TableRow>
            ))
          )}
        </TableBody>
      </Table>
    </div>
  );
}


