import { z } from "zod";

export const categorySchema = z.object({
  id: z.number().optional(),
  name: z
    .string()
    .min(1, "Category name is required")
    .max(100, "Category name must be less than 100 characters"),

  isActive: z.boolean(),

  subCategory: z.preprocess(
    (val) => (val === "" ? null : val),
    z.union([z.number(), z.null()])
  ),
});

export type CategoryForm = z.infer<typeof categorySchema>;


type CategoryResponse = {
  id: number;
  name: string;
  subCategory?: { id: number; name: string };
};







