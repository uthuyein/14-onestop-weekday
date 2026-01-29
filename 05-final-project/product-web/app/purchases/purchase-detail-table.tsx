import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"

type Props = {
  fields: any[]
  update: (index: number, value: any) => void
  remove: (index: number) => void
}

export function PurchaseDetailTable({ fields, update, remove }: Props) {
  return (
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>Product</TableHead>
          <TableHead>Price</TableHead>
          <TableHead>Qty</TableHead>
          <TableHead>Total</TableHead>
          <TableHead />
        </TableRow>
      </TableHeader>

      <TableBody>
        {fields.map((f, index) => {
          const total = f.qty * f.productPrice.price

          return (
            <TableRow key={f.id}>
              <TableCell>{f.productPrice.product.name}</TableCell>
              <TableCell>{f.productPrice.price}</TableCell>

              <TableCell className="w-24">
                <Input
                  type="number"
                  min={1}
                  value={f.qty}
                  onChange={(e) =>
                    update(index, {
                      ...f,
                      qty: Number(e.target.value),
                    })
                  }
                />
              </TableCell>

              <TableCell>{total}</TableCell>

              <TableCell>
                <Button
                  variant="destructive"
                  size="sm"
                  onClick={() => remove(index)}
                >
                  Remove
                </Button>
              </TableCell>
            </TableRow>
          )
        })}
      </TableBody>
    </Table>
  )
}
