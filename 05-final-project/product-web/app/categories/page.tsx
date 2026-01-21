import CategoryPage from "./CategoryPage";
import { getCategories } from "@/lib/server/category.server";

export default async function Page() {
  const categories = await getCategories(); // SERVER FETCH
  return <CategoryPage categories={categories} />;
}
