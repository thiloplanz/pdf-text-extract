//Copyright (c) 2011, 2014, Thilo Planz. All rights reserved.
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU Affero General Public License as
//published by the Free Software Foundation, either version 3 of the
//License, or (at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//GNU Affero General Public License for more details.
//
//You should have received a copy of the GNU Affero General Public License
//along with this program. If not, see <http://www.gnu.org/licenses/>.

package pdf_text_extract;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

public class Main {

	public static void main(String[] argv) throws IOException {

		if (argv.length != 3) {
			usage();
			return;
		}

		PdfReader reader;
		if ("-".equals(argv[0])) {
			reader = new PdfReader(System.in);

		} else {
			File pdf = new File(argv[0]);
			if (!pdf.canRead() || !pdf.isFile()) {
				System.err.println("cannot read input file "
						+ pdf.getAbsolutePath());
				return;
			}
			reader = new PdfReader(pdf.getAbsolutePath());
		}
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);

		int pageNumber = Integer.parseInt(argv[1]);

		PrintWriter out;
		if ("-".equals(argv[2])) {
			out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
		} else {
			File outputFile = new File(argv[2]);
			out = new PrintWriter(outputFile, "UTF-8");
		}

		parser.processContent(pageNumber, new DumpTextFragmentPositions(out));

		out.close();
		reader.close();
	}

	private static void usage() {
		System.err
				.println("pdf_text_extract <pdf file name> <page number> <output file name>");
		System.err
				.println("   reads the specified PDF document and writes the text fragments and their positions into the output file");
		System.err
				.println("      you can use `-` instead of file names to process the standard output and input streams");
		System.err.println();
		System.err
				.println("Copyright (c) 2011, 2014, Thilo Planz. All rights reserved.");
		System.err
				.println("  This program is free software under the terms of the GNU Affero General Public License");
		System.err
				.println("  Includes the iText PDF library, see http://itextpdf.com/");
	}

}
