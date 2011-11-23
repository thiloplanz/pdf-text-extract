//Copyright (c) 2011, Thilo Planz. All rights reserved.
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

import java.io.PrintWriter;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.LineSegment;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

class DumpTextFragmentPositions implements
		com.itextpdf.text.pdf.parser.TextExtractionStrategy {

	private final PrintWriter out;

	DumpTextFragmentPositions(PrintWriter out) {
		this.out = out;
	}

	@Override
	public String getResultantText() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void beginTextBlock() {
	}

	@Override
	public void endTextBlock() {
	}

	@Override
	public void renderImage(ImageRenderInfo renderInfo) {
	}

	@Override
	public void renderText(TextRenderInfo renderInfo) {
		LineSegment segment = renderInfo.getBaseline();
		out.format("%4.0f,%4.0f,%s\n", segment.getStartPoint().get(0), segment
				.getStartPoint().get(1), renderInfo.getText());

	}

}
