import MenuBar from "@/components/app/menu-bar";
import "./globals.css";


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <MenuBar />
        <main className="flex px-4 py-4">
          {children}
        </main>
      </body>
    </html>
  );
}
