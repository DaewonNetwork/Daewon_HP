import "@/(FSD)/shareds/styles/globalStyle.scss";
import { Metadata } from "next";
import RootProvider from "@/(FSD)/apps/providers/RootProvider";

export const metadata: Metadata = {

};

export default function RootLayout({ children }: { children: React.ReactNode; }) {
	return (
		<html lang="ko" suppressHydrationWarning>
			<head />
			<body>
				<RootProvider>
					{children}
				</RootProvider>
			</body>
		</html>
	);
}
