// type Category = {
//   id: number
//   name: string
//   parent?: { name: string }
//   isActive: boolean
// }

// const columns = [
//   {
//     key: "id",
//     header: "ID",
//     className: "w-[100px]",
//     cell: (cat: Category) => (
//       <span className="font-mono text-xs">#{cat.id}</span>
//     ),
//   },
//   {
//     key: "name",
//     header: "Category Name",
//     cell: (cat: Category) => (
//       <span className="font-medium">{cat.name}</span>
//     ),
//   },
//   {
//     key: "parent",
//     header: "Parent",
//     cell: (cat: Category) => cat.parent?.name || "—",
//   },
//   {
//     key: "status",
//     header: "Status",
//     cell: (cat: Category) => (
//       <span
//         className={`px-2 py-0.5 rounded-full text-[10px] font-bold uppercase ${
//           cat.isActive
//             ? "bg-green-100 text-green-700"
//             : "bg-slate-100 text-slate-600"
//         }`}
//       >
//         {cat.isActive ? "Active" : "Inactive"}
//       </span>
//     ),
//   },
//   {
//     key: "actions",
//     header: "Actions",
//     className: "text-right",
//     cell: (cat: Category) => (
//       <button
//         onClick={() => handleEdit(cat)}
//         className="inline-flex items-center justify-center w-8 h-8 hover:bg-slate-50 text-blue-600"
//       >
//         <Edit2 className="w-4 h-4" />
//       </button>
//     ),
//   },
// ]
