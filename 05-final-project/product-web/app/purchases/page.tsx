"use client"
import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { getProductPrices } from "@/lib/server/product.price.server";
import PurchasePage from "./PurchasePage";
import { ProductPriceForm, productPriceSchema, SelectProductPrice } from "@/lib/type/product-price-types";
import { SelectProductPriceTable } from "@/components/forms/tables/table-product-price";
import PurchaseDetailTable from "@/components/forms/tables/table-purchase-detail";

const prices = await getProductPrices();

export default function Page() {
  const form = useForm<ProductPriceForm>({
    resolver: zodResolver(productPriceSchema),
    defaultValues: {
      categoryId: undefined,
      productId: undefined,
      sizeId: undefined,
      priceType: "",
      id: undefined,
      price: 0,
      isActive: true,
      createAt: new Date(),
      updateAt: new Date(),
    },
  });

  const { reset, watch } = form;
  const categoryId = watch("categoryId");
  const productId = watch("productId");
  const sizeId = watch("sizeId");

  const categoryOptions = Array.from(
    new Map(prices.map(p => [p.category.id, p.category])).values()
  ).map(cat => ({ key: cat.id.toString(), value: cat.name }));

  const productOptions = Array.from(
    new Map(prices.map(p => [p.product.id, p.product])).values()
  ).map(prod => ({ key: prod.id.toString(), value: prod.name }));

  const sizeOptions = Array.from(
    new Map(prices.map(p => [p.size.id, p.size])).values()
  ).map(size => ({ key: size.id.toString(), value: size.name }));

  const filteredProducts = prices.filter((p) => {
    if (categoryId && p.category.id.toString() !== categoryId) return false;
    if (productId && p.product.id.toString() !== productId) return false;
    if (sizeId && p.size.id.toString() !== sizeId) return false;
    return true;
  });

  const [purchaseDetails, setPurchaseDetails] = useState<
    (SelectProductPrice & { qty: number })[]
  >([]);

  const handleSelect = (price: SelectProductPrice) => {
    if (!purchaseDetails.find(p => p.id === price.id)) {
      setPurchaseDetails([...purchaseDetails, { ...price, qty: 1 }]);
    }
  };

  const handleQtyChange = (id: number, qty: number) => {
    setPurchaseDetails(prev =>
      prev.map(p => (p.id === id ? { ...p, qty } : p))
    );
  };

  return (
    <div className="">
      <PurchasePage
        form={form}
        prices={prices}
        isEditMode={true}
        categoryOptions={categoryOptions}
        productOptions={productOptions}
        sizeOptions={sizeOptions}
      />
      <div className="flex gap-3 h-125 mx-6"> 
          <div className="w-120 border bg-white flex flex-col">
            <div className="overflow-auto flex-1">
              <SelectProductPriceTable prices={filteredProducts} select={handleSelect} />
            </div>
          </div>
          <div className="">
            <div className="flex-2 border h-80 bg-white flex flex-col">
              <div className="overflow-auto flex-1">
                <PurchaseDetailTable prices={purchaseDetails} onQtyChange={handleQtyChange} />
              </div>
          </div>
          <div className="">
            
          </div>
        </div>
      </div>
          
      </div>
   
  );
}
