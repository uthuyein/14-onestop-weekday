import { TableHeader, TableRow, TableHead, TableBody, TableCell } from "@/components/ui/table"
import { ProductPrice } from "@/lib/type/purchase-types"
import { Table } from "lucide-react"

type Props = {
  onSelect: (pp: ProductPrice) => void
}

export function ProductPriceTable({ onSelect }: Props) {
  return (
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>Product</TableHead>
          <TableHead>Price</TableHead>
        </TableRow>
      </TableHeader>
      {/* <TableBody>
        {prices.map((pp) => (
          <TableRow
            key={pp.id}
            className="cursor-pointer hover:bg-muted"
            onClick={() => onSelect(pp)}
          >
            <TableCell>{pp.product.name}</TableCell>
            <TableCell>{pp.price}</TableCell>
          </TableRow>
        ))}
      </TableBody> */}
    </Table>
  )
}
