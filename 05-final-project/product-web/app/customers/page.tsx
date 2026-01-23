"use server"

import { getCustomers } from "@/lib/server/customer.server";
import CustomerPage from "./CustomerPage";
import { getCategories } from "@/lib/server/category.server";

export default async function Page() {
  const customers = await getCustomers(); 
  return <CustomerPage customers={customers} />;
}
