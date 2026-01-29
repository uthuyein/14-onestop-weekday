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
import { SelectCustomer } from "@/lib/type/customer-types";

type CustomerTableProps = {
  customers: SelectCustomer[];
  loading?:boolean
  onEdit?: (cu: SelectCustomer) => void;
  onDelete?:(id: number) => void
};
export default function CustomerTable({customers,loading,onEdit,onDelete,}: CustomerTableProps) {

  if (loading) {
    return (
      <p className="text-center text-muted-foreground py-6">
        Loading...
      </p>
    );
  }

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
                colSpan={10}
                className="text-center py-8 text-muted-foreground"
              >
                No customers found.
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

                <TableCell>{cu.contact.email}</TableCell>
                <TableCell>{cu.contact.primaryPhone}</TableCell>
                <TableCell>{cu.contact.secondaryPhone}</TableCell>
                <TableCell>{cu.address.state}</TableCell>
                <TableCell>{cu.address.township}</TableCell>
                <TableCell>{cu.address.street}</TableCell>

                <TableCell className="text-right">
                  <div className="flex justify-end gap-2">
                    <Button
                      variant="ghost"
                      size="icon"
                      onClick={() => onEdit?.(cu)}
                      className="text-blue-600 hover:bg-blue-50"
                    >
                      <Edit2 className="w-4 h-4" />
                    </Button>

                    <Button
                      variant="ghost"
                      size="icon"
                      onClick={() => onDelete?.(cu.id)}
                      className="text-red-600 hover:bg-red-50"
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

