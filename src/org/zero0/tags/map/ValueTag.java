package org.zero0.tags.map;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings( "unchecked" )
public class ValueTag extends TagSupport implements MapConstants {
	private static final long serialVersionUID = -5181719720870252411L;

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			String value = ((Map.Entry<String, String>)pageContext.findAttribute( CURRENT_MAP_ENTRY )).getValue();
			
			System.err.println("map value end tag: " + value );
			if( value != null ) out.write( value );
		} catch( IOException e ) {
		}
		return EVAL_PAGE;
	}
}
