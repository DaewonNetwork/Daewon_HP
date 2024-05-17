import { Metadata } from "next";
import "@/styles/globals.css";

export const metadata: Metadata = {

};

export default function RootLayout({ children }: { children: React.ReactNode; }) {
	return (
		<html lang="en" suppressHydrationWarning>
			<head />
			<body>
				{children}
			</body>
		</html>
	);
}
