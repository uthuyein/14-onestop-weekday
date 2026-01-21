import { z } from "zod";

export const categorySchema = z.object({
  id: z.number().optional(),
  name: z
    .string()
    .nonempty( "Category name is required"),
  isActive: z.boolean(),

  subCategory: z.preprocess(
    (val) => (val === "" ? null : val),
    z.union([z.number(), z.null()])
  ),
});

export type CategoryForm = z.infer<typeof categorySchema>;








