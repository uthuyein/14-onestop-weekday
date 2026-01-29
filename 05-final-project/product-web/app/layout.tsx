import MenuBar from "@/components/app/menu-bar";
import "./globals.css";
import { Toaster } from "sonner";


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body suppressHydrationWarning>
        <MenuBar />
        <main className="w-full flex px-4 py-4">
          {children}
        </main>
        <Toaster position="top-right" duration={5*1000}/>
      </body>
    </html>
  );
}
