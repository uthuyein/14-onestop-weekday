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
import { CategoryListItem } from "@/lib/type/category-types";
import { Button } from "@/components/ui/button";



type CategoryTableProps = {
  categories: CategoryListItem[];
  onEdit: (cat: CategoryListItem) => void;
  handleDeactivate:(id: number) => void;
};

export default function CategoryTable({categories,onEdit,handleDeactivate}: CategoryTableProps) {
  return (
    <div className="rounded-md border bg-white">
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Category Name</TableHead>
            <TableHead>Parent</TableHead>
            <TableHead>Status</TableHead>
            <TableHead className="text-right">Actions</TableHead>
          </TableRow>
        </TableHeader>

        <TableBody>
          {categories.length === 0 ? (
            <TableRow>
              <TableCell
                colSpan={5}
                className="text-center py-8 text-muted-foreground"
              >
                No categories found.
              </TableCell>
            </TableRow>
          ) : (
            categories.map((cat) => (
              <TableRow key={cat.id}>
                <TableCell className="font-mono text-xs">
                  #{cat.id}
                </TableCell>

                <TableCell className="font-medium">
                  {cat.name}
                </TableCell>

                <TableCell>
                  {cat.subCategory?.name || "—"}
                </TableCell>

                <TableCell>
                  <span
                    className={`px-2 py-0.5 rounded-full text-[10px] font-bold uppercase ${
                      cat.isActive
                        ? "bg-green-100 text-green-700"
                        : "bg-slate-100 text-slate-600"
                    }`}
                  >
                    {cat.isActive ? "Active" : "Inactive"}
                  </span>
                  </TableCell>
                  <TableCell className="text-right">
                    <div className="flex justify-end gap-2">
                      <Button
                        onClick={() => onEdit(cat)}
                        className="hover:bg-blue-50 text-blue-600 bg-bg-light"
                      >
                        <Edit2 className="w-4 h-4" />
                      </Button>
                     <Button
                        onClick={() => handleDeactivate(cat.id)}
                        className=" hover:bg-red-50 rounded text-red-600 bg-bg-light"
                      >
                        <Trash2 className="w-4 h-4" />
                      </Button>
                    </div>
                  </TableCell>
                {/* 
                <TableCell className="text-right">
                  
                  <button
                    onClick={() => onEdit(cat)}
                    className="inline-flex items-center justify-center w-8 h-8 hover:bg-slate-50 text-blue-600 transition-colors"
                  >
                    <Edit2 className="w-4 h-4" />
                  </button>
                </TableCell> */}
              </TableRow>
            ))
          )}
        </TableBody>
      </Table>
    </div>
  );
}
