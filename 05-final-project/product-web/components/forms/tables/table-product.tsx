"use client";

import { Edit2 } from "lucide-react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { ProductListItem } from "@/lib/type/product-types";



type CategoryTableProps = {
  products: ProductListItem[];
  onEdit: (prod: ProductListItem) => void;
};

export default function ProductTable({products,onEdit}: CategoryTableProps) {
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
                  <button
                    onClick={() => onEdit(prod)}
                    className="inline-flex items-center justify-center w-8 h-8 hover:bg-slate-50 text-blue-600 transition-colors"
                  >
                    <Edit2 className="w-4 h-4" />
                  </button>
                </TableCell>
              </TableRow>
            ))
          )}
        </TableBody>
      </Table>
    </div>
  );
}
