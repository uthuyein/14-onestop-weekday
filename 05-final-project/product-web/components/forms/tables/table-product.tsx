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
import { ProductListItem } from "@/lib/type/product-types";
import { Button } from "@/components/ui/button";



type CategoryTableProps = {
  products: ProductListItem[];
  onEdit: (prod: ProductListItem) => void;
  onDelete:(prod: ProductListItem,active:boolean ) => void
};

export default function ProductTable({products,onEdit,onDelete}: CategoryTableProps) {
  return (
    <div className="border bg-white ">
      <Table  className="table-fixed w-full ">
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Product</TableHead>
            <TableHead>Category</TableHead>
            <TableHead>Status</TableHead>
            <TableHead className="text-right w-[120px]">Actions</TableHead>
          </TableRow>
        </TableHeader>

        <TableBody>
          {products.length === 0 ? (
            <TableRow>
              <TableCell
                colSpan={5}
                className="text-center py-8 text-muted-foreground"
              >
                No categories found.
              </TableCell>
            </TableRow>
          ) : (
            products.map((prod) => (
              <TableRow key={prod.id}>
                <TableCell className="font-mono text-xs">
                  #{prod.id}
                </TableCell>

                <TableCell className="font-medium">
                  {prod.name}
                </TableCell>

                <TableCell>
                  {prod.category.name || "—"}
                </TableCell>

                <TableCell>
                  <span
                    className={`px-2 py-0.5 rounded-full text-[10px] font-bold uppercase ${
                      prod.isActive
                        ? "bg-green-100 text-green-700"
                        : "bg-slate-100 text-slate-600"
                    }`}
                  >
                    {prod.isActive ? "Active" : "Inactive"}
                  </span>
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
