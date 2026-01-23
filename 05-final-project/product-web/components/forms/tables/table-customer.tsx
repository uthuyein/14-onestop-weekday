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
import { Button } from "@/components/ui/button";
import { CustomerListItem } from "@/lib/type/customer-types";



type CustomerTableProps = {
  customers: CustomerListItem[];
  onEdit: (cu: CustomerListItem) => void;
  onDelete:(cu: CustomerListItem,active : boolean) => void;
};

export default function CustomerTable({customers,onEdit,onDelete}: CustomerTableProps) {
  return (
    <div className="rounded-md border bg-white">
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Customer Name</TableHead>
            <TableHead>Member Type</TableHead>
            <TableHead>Email</TableHead>
            <TableHead>Primary</TableHead>
            <TableHead>Secondary</TableHead>
            <TableHead>State</TableHead>
            <TableHead>Township</TableHead>
            <TableHead>Street</TableHead>
            <TableHead className="text-right">Actions</TableHead>
          </TableRow>
        </TableHeader>

        <TableBody>
          {customers.length === 0 ? (
            <TableRow>
              <TableCell
                colSpan={5}
                className="text-center py-8 text-muted-foreground"
              >
                No categories found.
              </TableCell>
            </TableRow>
          ) : (
            customers.map((cu) => (
              <TableRow key={cu.id}>
                <TableCell className="font-mono text-xs">
                  #{cu.id}
                </TableCell>

                <TableCell className="font-medium">
                  {cu.name}
                </TableCell>

                <TableCell>
                  {cu.memberType || "—"}
                </TableCell>

                <TableCell>
                  {cu.contact.email}
                </TableCell>
                <TableCell>
                  {cu.contact.primaryPhone}
                </TableCell>
                <TableCell>
                  {cu.contact.secondaryPhone}
                </TableCell>
                <TableCell>
                  {cu.address.state}
                </TableCell>
                <TableCell>
                  {cu.address.township}
                </TableCell>
                <TableCell>
                  {cu.address.street}
                </TableCell>
                  <TableCell className="text-right">
                    <div className="flex justify-end gap-2">
                      <Button
                        onClick={() => onEdit(cu)}
                        className="hover:bg-blue-50 text-blue-600 bg-bg-light"
                      >
                        <Edit2 className="w-4 h-4" />
                      </Button>
                     <Button
                        onClick={() => onDelete(cu,false)}
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
